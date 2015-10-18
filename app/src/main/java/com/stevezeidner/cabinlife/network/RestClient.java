package com.stevezeidner.cabinlife.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stevezeidner.cabinlife.CabinLifeApplication;
import com.stevezeidner.cabinlife.core.Constants;
import com.stevezeidner.cabinlife.di.DaggerScope;
import com.stevezeidner.cabinlife.network.service.PostsService;

import javax.inject.Inject;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@DaggerScope(CabinLifeApplication.Component.class)
public class RestClient {

    private PostsService postsService;

    @Inject
    public RestClient() {

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postsService = retrofit.create(PostsService.class);
    }

    public PostsService getPostsService() {
        return postsService;
    }
}
