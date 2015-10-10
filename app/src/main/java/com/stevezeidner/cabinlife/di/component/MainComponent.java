package com.stevezeidner.cabinlife.di.component;

import com.stevezeidner.cabinlife.MainActivity;
import com.stevezeidner.cabinlife.di.module.MainModule;
import com.stevezeidner.cabinlife.network.service.PostsService;

import dagger.Component;

/**
 * Created by szeidner on 10/9/15.
 */
@Component(
        modules = {
                MainModule.class
        }
)
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(PostsService postsService);
}
