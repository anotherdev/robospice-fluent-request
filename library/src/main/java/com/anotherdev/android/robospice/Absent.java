package com.anotherdev.android.robospice;

import javax.annotation.Nullable;

final class Absent<T> extends Optional<T> {

    static final Absent<Object> INSTANCE = new Absent<>();


    @SuppressWarnings("unchecked") // implementation is "fully variant"
    static <T> Optional<T> withType() {
        return (Optional<T>) INSTANCE;
    }


    private Absent() {}

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override
    public T or(T defaultValue) {
        return checkNotNull(defaultValue, "Use Optional.orNull() instead of Optional.or(null)");
    }

    @Nullable
    @Override
    public T orNull() {
        return null;
    }
}
