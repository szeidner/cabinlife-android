package com.stevezeidner.cabinlife.di;

import android.content.Context;

/**
 * Created by szeidner on 10/10/15.
 */
public class DaggerService {
    public static final String SERVICE_NAME = DaggerService.class.getName();

    /**
     * Caller is required to know the type of the component for this context.
     */
    @SuppressWarnings("unchecked") //
    public static <T> T getDaggerComponent(Context context) {
        //noinspection ResourceType
        return (T) context.getSystemService(SERVICE_NAME);
    }

}
