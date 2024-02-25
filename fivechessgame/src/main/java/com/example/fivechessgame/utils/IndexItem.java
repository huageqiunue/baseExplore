package com.example.fivechessgame.utils;

public class IndexItem<T> {

    private final int index;
    private final T item;

    public IndexItem(int index, T item) {
        this.index = index;
        this.item = item;
    }

    public int getIndex() {
        return index;
    }

    public T getItem() {
        return item;
    }
}

