package com.stevezeidner.cabinlife.core;

import flownavigation.path.Path;

/**
 * Class to extend flow navigation path and add a title
 */
public class CabinLifePath extends Path {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
