package com.contentsquaregit.Tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.contentsquaregit.R;

/**
 * Created by thiba_000 on 05/11/2016.
 */

public class InternetConnection {

    public static boolean testingNetwork(Context context)
    {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(context, R.string.main_network_error, Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
