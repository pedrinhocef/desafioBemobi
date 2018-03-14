package com.pedrosoares.desafiobemobi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pedrosoares.desafiobemobi.R;

public class TratarConexao extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_conexao);

        tentarConexao();

    }

    private void tentarConexao() {
        Button btn = findViewById(R.id.botao_carregar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TratarConexao.this, MainActivity.class);
                if (isOnline()) {
                    startActivity(intent);
                } else {
                    Toast.makeText(TratarConexao.this, "Ainda sem conexão!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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