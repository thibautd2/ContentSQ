package com.contentsquaregit.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.contentsquaregit.R;
import com.contentsquaregit.activity.RepoListActivity;
import com.contentsquaregit.adapter.RepoListAdapter;
import com.contentsquaregit.model.Repository;

import java.io.BufferedOutputStream;
import java.util.ArrayList;


/**
 * Created by thiba_000 on 03/11/2016.
 */

public class GetRepoList extends AsyncTask<String, Void, ArrayList<Repository>> {
    private ArrayList<Pair<String,String>> arguments;
    public static ArrayList<Repository> repositories = new ArrayList<>();
    public static String lastIndex = "0";
    public static Boolean loading = false;

    private ProgressDialog progressDialog;
    private Context context;
    private Boolean first;
    private RepoListAdapter adapter;

    public GetRepoList(ArrayList<Pair<String,String>> arguments, Context context, boolean first, RepoListAdapter adapter)
    {
        this.arguments = arguments;
        this.context = context;
        this.first = first;
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<Repository> doInBackground(String... urls) {
        return WebServices.getRepositories(arguments);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = true;
        if(first) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(true);
            progressDialog.setTitle(context.getString(R.string.main_progress_title));
            progressDialog.setMessage(context.getString(R.string.main_progress_message));
            progressDialog.show();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Repository> result) {
        loading = false;
        if(progressDialog != null)
            progressDialog.dismiss();
        if(result != null) {
            repositories.addAll(result);
            lastIndex = repositories.get(repositories.size() - 1).getId();
            if(first)
                context.startActivity(new Intent(context, RepoListActivity.class));
            else
                adapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(context, context.getString(R.string.main_api_error), Toast.LENGTH_LONG).show();


    }
}