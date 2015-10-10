package com.stevezeidner.cabinlife.di;


import com.stevezeidner.cabinlife.di.component.MainComponent;

/**
 * Created by szeidner on 9/8/15.
 */
public enum Injector {
    INSTANCE;

    private MainComponent mainComponent;


    private Injector(){
    }

    public void initializeApplicationComponent() {
//        MainComponent mainComponent = DaggerMainComponent.builder().build();
//        this.mainComponent = mainComponent;
    }


    public MainComponent getMainComponent() {
        return mainComponent;
    }
}