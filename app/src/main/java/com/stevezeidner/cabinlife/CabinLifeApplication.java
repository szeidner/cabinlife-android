package com.stevezeidner.cabinlife;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.stevezeidner.cabinlife.di.Injector;

import mortar.MortarScope;
import timber.log.Timber;

/**
 * Created by szeidner on 10/9/15.
 */
public class CabinLifeApplication extends Application {

    private MortarScope rootScope;

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

    @Override
    public Object getSystemService(String name) {
        if (rootScope == null) {
            rootScope = MortarScope.buildRootScope().build("Root");
        }

        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }

}
