package com.pedrosoares.desafiobemobi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.pedrosoares.desafiobemobi.R;

public class MainActivity extends AppCompatActivity {

    private Intent mTratarConexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTratarConexao = new Intent(this, TratarConexao.class);

        ImageButton btn_kids = findViewById(R.id.btn_go_kids);
        btn_kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProKids = new Intent(MainActivity.this, AppsKids.class);
                startActivity(vaiProKids);
            }
        });

        ImageButton btn_games = findViewById(R.id.btn_go_game);
        btn_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProGames = new Intent(MainActivity.this, AppsGames.class);
                startActivity(vaiProGames);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isOnline()) {
            startActivity(mTratarConexao);
        }
    }

    public boolean isOnline(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if ( manager != null ) {
            manager.getActiveNetworkInfo();
            if (manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return true;
            }
            if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return true;
            }
        }
        return false;
    }
}
