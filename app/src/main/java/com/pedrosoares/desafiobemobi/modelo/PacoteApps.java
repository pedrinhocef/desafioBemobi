package com.pedrosoares.desafiobemobi.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PacoteApps implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;
    private String tamanho;
    private String cover;
    private String capaDetalhes;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getCover() {
        return cover;
    }

    public String getCapaDetalhes() {
        return capaDetalhes;
    }
}
