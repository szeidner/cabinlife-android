package com.stevezeidner.cabinlife.mortar;

public interface ScreenComponentFactory<T> {
    Object createComponent(T parent);
}
