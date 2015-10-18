package com.stevezeidner.cabinlife.network.service;

import com.stevezeidner.cabinlife.network.model.Post;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by szeidner on 10/9/15.
 */
public interface PostsService {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
