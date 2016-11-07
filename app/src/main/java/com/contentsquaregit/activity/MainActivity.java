package com.contentsquaregit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.contentsquaregit.R;
import com.contentsquaregit.service.GetRepoList;

import static com.contentsquaregit.Tools.InternetConnection.testingNetwork;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(testingNetwork(this)) {
            GetRepoList.repositories.clear();
            new GetRepoList(null, this, true, null).execute();
        }
    }


}


