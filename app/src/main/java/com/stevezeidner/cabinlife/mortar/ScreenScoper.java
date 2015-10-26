package com.stevezeidner.cabinlife.mortar;

import android.content.Context;

import com.stevezeidner.cabinlife.di.DaggerService;

import flownavigation.path.Path;
import mortar.MortarScope;

public class ScreenScoper {

    public MortarScope getScreenScope(Context context, String name, Path path) {
        MortarScope parentScope = MortarScope.getScope(context);

        MortarScope childScope = parentScope.findChild(name);
        if (childScope != null) {
            return childScope;
        }

        if (!(path instanceof ScreenComponentFactory)) {
            throw new IllegalStateException("Path must imlement ComponentFactory");
        }
        ScreenComponentFactory screenComponentFactory = (ScreenComponentFactory) path;
        Object component = screenComponentFactory.createComponent(parentScope.getService(DaggerService.SERVICE_NAME));

        MortarScope.Builder builder = parentScope.buildChild()
                .withService(DaggerService.SERVICE_NAME, component);

        return builder.build(name);
    }
}
