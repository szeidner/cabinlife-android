package com.stevezeidner.cabinlife;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.stevezeidner.cabinlife.di.AppDependencies;
import com.stevezeidner.cabinlife.di.DaggerScope;
import com.stevezeidner.cabinlife.di.DaggerService;

import mortar.MortarScope;
import timber.log.Timber;

/**
 * Created by szeidner on 10/9/15.
 */
public class CabinLifeApplication extends Application {

    private MortarScope mortarScope;

    @Override
    public void onCreate() {
        super.onCreate();

        // start logger
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        // start crashlytics
        Crashlytics.start(this);

        // start leak detection
        LeakCanary.install(this);

        if (mortarScope == null) {

            Component component = DaggerCabinLifeApplication_Component.create();
            component.inject(this);

            mortarScope = MortarScope.buildRootScope()
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build("Root");
        }

    }

    @Override
    public Object getSystemService(String name) {
        if (mortarScope == null) {
            Component component = DaggerCabinLifeApplication_Component.create();
            mortarScope = MortarScope.buildRootScope()
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build("Root");
        }

        return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
    }


    @dagger.Component
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(CabinLifeApplication app);
    }
}
