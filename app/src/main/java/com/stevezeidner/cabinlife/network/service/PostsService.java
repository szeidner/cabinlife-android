package com.stevezeidner.cabinlife.network.service;

import com.stevezeidner.cabinlife.di.Injector;
import com.stevezeidner.cabinlife.network.model.Post;

import java.util.List;

import javax.inject.Inject;

import retrofit.Retrofit;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by szeidner on 10/9/15.
 */
public class PostsService {
    @Inject
    Retrofit retrofit;

    private PostsAPI postsAPI;

    public PostsService() {
        Injector.INSTANCE.getMainComponent().inject(this);

        postsAPI = retrofit.create(PostsAPI.class);
    }

    public PostsAPI getAPI() {
        return postsAPI;
    }

    public interface PostsAPI {
        @GET("posts")
        public Observable<List<Post>>
            getPosts();
    }
}
