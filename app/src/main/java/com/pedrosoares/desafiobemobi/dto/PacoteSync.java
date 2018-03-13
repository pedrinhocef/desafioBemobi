package com.pedrosoares.desafiobemobi.dto;

import com.pedrosoares.desafiobemobi.modelo.PacoteApps;

import java.io.Serializable;
import java.util.ArrayList;

public class PacoteSync implements Serializable {

    private ArrayList<PacoteApps> aplicativos;

    public ArrayList<PacoteApps> getAplicativos(){
        return aplicativos;
    }
}
