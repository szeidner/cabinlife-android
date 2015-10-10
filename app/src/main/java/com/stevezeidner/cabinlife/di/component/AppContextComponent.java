package com.stevezeidner.cabinlife.di.component;

import android.content.Context;
import android.location.LocationManager;

import com.stevezeidner.cabinlife.CabinLifeApplication;

/**
 * Created by szeidner on 10/10/15.
 */
public interface AppContextComponent {
    CabinLifeApplication application();
    Context applicationContext();
    LocationManager locationManager();
}
