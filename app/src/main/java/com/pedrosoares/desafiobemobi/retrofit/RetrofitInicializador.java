package com.pedrosoares.desafiobemobi.retrofit;

import com.pedrosoares.desafiobemobi.services.PacoteService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;
    private final String URL = "https://private-69bb5f-bemobikids.apiary-mock.com/";

    public RetrofitInicializador() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

                retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(JacksonConverterFactory.create())
                        .client(client.build())
                        .build();
    }

    public PacoteService getPacoteService(){
        return retrofit.create(PacoteService.class);
    }
}
