package com.contentsquaregit.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.contentsquaregit.R;
import com.contentsquaregit.activity.RepositoryActivity;
import com.contentsquaregit.model.Repository;

import java.util.ArrayList;

/**
 * Created by thiba_000 on 04/11/2016.
 */

public class GetRepositoryDetails extends AsyncTask<String, Void, Repository> {

    private Context context;
    private ProgressDialog progressDialog;
    private String repoFullName;

    public GetRepositoryDetails(Context context,String repoFullName )
    {
        this.context = context;
        this.repoFullName = repoFullName;
    }


    @Override
    protected Repository doInBackground(String... strings) {

        return WebServices.getRepository(repoFullName);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setTitle(context.getString(R.string.repo_list_progress_title));
        progressDialog.setMessage(context.getString(R.string.main_progress_message));
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Repository repository) {
        super.onPostExecute(repository);
        progressDialog.dismiss();
        if(repository != null) {
            Intent intent = new Intent(context, RepositoryActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("repository", repository);
            intent.putExtras(b);
            context.startActivity(intent);
        }
        else
            Toast.makeText(context, context.getString(R.string.repo_list_network_error), Toast.LENGTH_LONG).show();

    }
}
