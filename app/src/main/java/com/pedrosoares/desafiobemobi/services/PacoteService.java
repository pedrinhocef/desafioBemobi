package com.pedrosoares.desafiobemobi.services;

import com.pedrosoares.desafiobemobi.dto.PacoteSync;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PacoteService {

    @GET("kids")
    Call <PacoteSync> buscarKids();

    @GET("games")
    Call <PacoteSync> buscarGames();
}
