package com.pedrosoares.desafiobemobi.modelo;

import java.io.Serializable;

public class PacoteApps implements Serializable {

    private String name;
    private String description;
    private String price;
    private int tamanho;
    private String cover;
    private String capaDetalhes;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String getCover() {
        return cover;
    }

    public String getCapaDetalhes() {
        return capaDetalhes;
    }
}
