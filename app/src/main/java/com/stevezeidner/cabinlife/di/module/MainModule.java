package com.stevezeidner.cabinlife.di.module;

import com.stevezeidner.cabinlife.core.Constants;
import com.stevezeidner.cabinlife.network.service.PostsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by szeidner on 10/9/15.
 */
@Module
public class MainModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PostsService providePostsService() {
        return new PostsService();
    }

}
