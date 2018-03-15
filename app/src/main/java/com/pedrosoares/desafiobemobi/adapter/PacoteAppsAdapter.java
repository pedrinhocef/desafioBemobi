package com.pedrosoares.desafiobemobi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrosoares.desafiobemobi.R;
import com.pedrosoares.desafiobemobi.modelo.PacoteApps;
import com.pedrosoares.desafiobemobi.util.MoedaUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PacoteAppsAdapter extends BaseAdapter{

    private List<PacoteApps> pacoteAppsList;
    private Context context;

    public PacoteAppsAdapter(List<PacoteApps> pacoteApps, Context context){
        this.pacoteAppsList = pacoteApps;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacoteAppsList.size();
    }

    @Override
    public Object getItem(int position) {
        return pacoteAppsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PacoteApps pacoteApps = pacoteAppsList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.item_pacote_apps, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.item_pacote_imagem_app);
        Picasso.with(context).load(pacoteApps.getCover()).placeholder(R.mipmap.ic_launcher).into(imageView);

        TextView tituloApp = view.findViewById(R.id.item_pacote_name);
        tituloApp.setText(pacoteApps.getName());

        TextView tamanhoApp = view.findViewById(R.id.item_pacote_tamanho);
        tamanhoApp.setText(pacoteApps.getTamanho());

        TextView priceApp = view.findViewById(R.id.item_pacote_preco);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacoteApps.getPrice());
        priceApp.setText(moedaBrasileira);

        return view;
    }
}
