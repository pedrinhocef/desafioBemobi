package com.pedrosoares.desafiobemobi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pedrosoares.desafiobemobi.R;

public class MainActivity extends AppCompatActivity {

    private Intent tratarConexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tratarConexao = new Intent(this, TratarConexao.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnline()){

        } else {
            startActivity(tratarConexao);
        }
    }

    public boolean isOnline(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( manager != null ) {
            manager.getActiveNetworkInfo();
            //Verifica internet pela WIFI
            if (manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return true;
            }
            //Verifica se tem internet móvel
            if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return true;
            }
        }
        return false;
    }
}
