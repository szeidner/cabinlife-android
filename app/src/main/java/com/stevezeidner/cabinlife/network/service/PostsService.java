package com.stevezeidner.cabinlife.network.service;

import com.stevezeidner.cabinlife.network.model.Post;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by szeidner on 10/9/15.
 */
public interface PostsService {
    @GET("post")
    Observable<List<Post>> getPosts();

    @GET("post/{id}")
    Observable<Post> getPost(@Path("id") int postId);
}
