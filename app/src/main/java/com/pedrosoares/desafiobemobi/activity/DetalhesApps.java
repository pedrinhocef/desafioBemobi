package com.pedrosoares.desafiobemobi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pedrosoares.desafiobemobi.R;
import com.pedrosoares.desafiobemobi.modelo.PacoteApps;
import com.pedrosoares.desafiobemobi.util.MoedaUtil;
import com.squareup.picasso.Picasso;

public class DetalhesApps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        setTitle(getString(R.string.titulo_detalhes_app));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView imagemDoApp = findViewById(R.id.detalhes_imagem_app);
        Button btnBaixarApp = findViewById(R.id.detalhes_botao_baixar_app);
        TextView nomeDoApp = findViewById(R.id.detalhes_nome_app);
        TextView descDoApp = findViewById(R.id.detalhes_descricao_app);
        TextView tamanhoDoApp = findViewById(R.id.detalhes_tamanho_app);
        TextView precoDoApp = findViewById(R.id.detalhes_preco_do_app);

        final Intent intent = getIntent();
        final PacoteApps pacoteApps = (PacoteApps) intent
                .getSerializableExtra("pacoteApps");

        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacoteApps.getPrice());

        nomeDoApp.setText(pacoteApps.getName());
        precoDoApp.setText(moedaBrasileira);
        tamanhoDoApp.setText(pacoteApps.getTamanho());
        descDoApp.setText(pacoteApps.getDescription());
        Picasso.with(this).load(pacoteApps.getCapaDetalhes())
                .placeholder(R.mipmap.ic_launcher).into(imagemDoApp);

        btnBaixarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalhesApps.this, getString(R.string.baixando_app)
                        + pacoteApps.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
