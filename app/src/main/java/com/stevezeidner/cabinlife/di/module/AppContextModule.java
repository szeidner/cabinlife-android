package com.stevezeidner.cabinlife.di.module;

import android.content.Context;
import android.location.LocationManager;

import com.stevezeidner.cabinlife.CabinLifeApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {
    private final CabinLifeApplication application;

    public AppContextModule(CabinLifeApplication application) {
        this.application = application;
    }

    @Provides
    public CabinLifeApplication application() {
        return this.application;
    }

    @Provides
    public Context applicationContext() {
        return this.application;
    }

    @Provides
    public LocationManager locationService(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
