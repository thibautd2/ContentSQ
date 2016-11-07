package com.contentsquaregit.service;

import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.Log;
import com.contentsquaregit.model.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by thiba_000 on 03/11/2016.
 */
 class WebServices {

    private static final String URL = "https://api.github.com/";
    private static final String REPOSITORIES = "repositories";
    private static final String REPOSITORY = "repos/";

    @Nullable
    private static InputStream sendRequest(java.net.URL url, String method, ArrayList<Pair<String, String>> parameters) throws Exception {
        try {
            if(parameters != null) {
                String params = "?";
                for(Pair<String,String> param : parameters)
                    params += param.first +"="+param.second+"&";

                url = new URL(url+params);
            }
            Log.e("URL : ", url.toString());
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setReadTimeout(10000);
            client.setConnectTimeout(15000);
            client.setRequestMethod(method);

            if (client.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return client.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return null;
    }

    @Nullable
    static ArrayList<Repository> getRepositories (ArrayList<Pair<String, String>> parameters) {
        try {
            InputStream inputStream = sendRequest(new URL(URL + REPOSITORIES), "GET", parameters);
            if (inputStream != null) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                Type listType = new TypeToken<ArrayList<Repository>>(){}.getType();
                return new Gson().fromJson(responseStrBuilder.toString(), listType);
            }
        } catch (Exception e) {
            Log.e("WS: getRepositories", "can't fetch data");
        }
        return null;
    }

    @Nullable
    static Repository getRepository (String url) {
        try {
            InputStream inputStream = sendRequest(new URL(URL + REPOSITORY + url), "GET", null);
            if (inputStream != null) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                return new Gson().fromJson(responseStrBuilder.toString(), Repository.class);
            }
        } catch (Exception e) {
            Log.e("WS: getRepository", "can't fetch data");
        }
        return null;
    }
}
