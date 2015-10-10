package com.stevezeidner.cabinlife;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.stevezeidner.cabinlife.di.Injector;

import timber.log.Timber;

/**
 * Created by szeidner on 10/9/15.
 */
public class CabinLifeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // inject self
        Injector.INSTANCE.initializeMainComponent(this);

        // start crashlytics
        Crashlytics.start(this);

        // start leak detection
        LeakCanary.install(this);

        // start logger
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
