package com.stevezeidner.cabinlife.di;


import com.stevezeidner.cabinlife.CabinLifeApplication;
import com.stevezeidner.cabinlife.di.component.DaggerMainComponent;
import com.stevezeidner.cabinlife.di.component.MainComponent;
import com.stevezeidner.cabinlife.di.module.AppContextModule;

/**
 * Created by szeidner on 9/8/15.
 */
public enum Injector {
    INSTANCE;

    private MainComponent mainComponent;


    private Injector(){
    }

    public void initializeMainComponent(CabinLifeApplication application) {
        MainComponent mainComponent = DaggerMainComponent.builder()
                .appContextModule(new AppContextModule(application))
                .build();
        this.mainComponent = mainComponent;
    }


    public MainComponent getMainComponent() {
        return mainComponent;
    }
}