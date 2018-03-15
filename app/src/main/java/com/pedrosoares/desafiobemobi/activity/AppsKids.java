package com.pedrosoares.desafiobemobi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pedrosoares.desafiobemobi.R;
import com.pedrosoares.desafiobemobi.adapter.PacoteAppsAdapter;
import com.pedrosoares.desafiobemobi.dto.PacoteSync;
import com.pedrosoares.desafiobemobi.modelo.PacoteApps;
import com.pedrosoares.desafiobemobi.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppsKids extends AppCompatActivity {

    static final String STATE_ACTIVITY = "salvando";
    private ListView mListView;
    private PacoteAppsAdapter mAdapter;
    private PacoteApps mPacoteApps;
    private Intent mTratarConexao;
    private PacoteSync mPacoteSync;
    private SwipeRefreshLayout mRefresh;

    protected void onCreate(Bundle savedIntanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_list_view);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTratarConexao = new Intent(this, TratarConexao.class);

        mListView = findViewById(R.id.lv_pacotes);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPacoteApps = (PacoteApps) mListView.getItemAtPosition(position);
                Intent vaiProDetalhes = new Intent(AppsKids.this, DetalhesApps.class);
                vaiProDetalhes.putExtra("pacoteApps", mPacoteApps);
                startActivity(vaiProDetalhes);
            }
        });


        mRefresh = findViewById(R.id.refresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RetrofitKids();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnline()) {
            RetrofitKids();
        } else {
            startActivity(mTratarConexao);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(STATE_ACTIVITY, mPacoteSync);
        Log.e("OnsavedChamado", String.valueOf(outState));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPacoteSync = (PacoteSync) savedInstanceState.getSerializable(STATE_ACTIVITY);
        Log.e("OnRestore", String.valueOf(mPacoteSync));
    }

    public void RetrofitKids() {
        Call<PacoteSync> call = new RetrofitInicializador().getPacoteService().buscarKids();

        call.enqueue(new Callback<PacoteSync>() {
            @Override
            public void onResponse(@NonNull Call<PacoteSync>  call, @NonNull Response<PacoteSync> response) {

                if (response.isSuccessful()) {
                    mPacoteSync = response.body();
                    mAdapter = null;

                    if (mPacoteSync != null) {
                        mAdapter = new PacoteAppsAdapter(mPacoteSync.getAplicativos(), AppsKids.this);
                    }

                    mListView.setAdapter(mAdapter);
                    mRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PacoteSync> call, @NonNull Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
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
