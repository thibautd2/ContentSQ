package com.contentsquaregit.activity;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.contentsquaregit.R;
import com.contentsquaregit.Tools.InternetConnection;
import com.contentsquaregit.adapter.RepoListAdapter;
import com.contentsquaregit.service.GetRepoList;
import java.util.ArrayList;

/**
 * Created by thiba_000 on 03/11/2016.
 */

public class RepoListActivity extends AppCompatActivity{

    private int back = 0;
    private RecyclerView.LayoutManager LayoutManager;
    private final int reloadRange = 35;
    private RepoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        initRecyclerView();
    }

    private RecyclerView.OnScrollListener initScrollListener()
    {
        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = LayoutManager.getItemCount();
                int lastVisibleItemPosition = ((LinearLayoutManager) LayoutManager).findLastVisibleItemPosition();
                Log.e("las", ""+lastVisibleItemPosition);
                Log.e("total", ""+totalItemCount);
                if (!GetRepoList.loading && lastVisibleItemPosition + reloadRange > totalItemCount) {
                    Pair<String, String> arg = new Pair<>("since", GetRepoList.lastIndex);
                    ArrayList<Pair<String,String>> arguments = new ArrayList<>();
                    arguments.add(arg);
                    if(InternetConnection.testingNetwork(getApplicationContext()))
                        new GetRepoList(arguments, getApplicationContext(), false, adapter).execute();
                }
                if(lastVisibleItemPosition == totalItemCount - 1)
                    Toast.makeText(getBaseContext(), getString(R.string.repo_list_more_result), Toast.LENGTH_SHORT).show();
            }
        };
        return scrollListener;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.repo_list_recycler);
        recyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RepoListAdapter(GetRepoList.repositories, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(initScrollListener());
    }

    @Override
    protected void onResume() {
        back = 0;
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if(back == 0)
            Toast.makeText(this, R.string.repo_list_leave, Toast.LENGTH_SHORT).show();
        else
            super.onBackPressed();
        back++;
    }
}
