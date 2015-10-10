package com.stevezeidner.cabinlife.di.component;

import com.stevezeidner.cabinlife.MainActivity;
import com.stevezeidner.cabinlife.di.module.AppContextModule;
import com.stevezeidner.cabinlife.di.module.MainModule;
import com.stevezeidner.cabinlife.network.service.PostsService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by szeidner on 10/9/15.
 */
@Singleton
@Component(
        modules = {
                AppContextModule.class,
                MainModule.class
        }
)
public interface MainComponent extends AppContextComponent {
    void inject(MainActivity mainActivity);
    void inject(PostsService postsService);
}
