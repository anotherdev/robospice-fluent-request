package com.anotherdev.android.robospice;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class Present<T> extends Optional<T> {

    private final T mReference;


    Present(@Nonnull T reference) {
        mReference = reference;
    }

    @Override
    public boolean isPresent() {
        return true;
    }

    @Override
    public T get() {
        return mReference;
    }

    @Override
    public T or(T defaultValue) {
        checkNotNull(defaultValue, "Use Optional.orNull() instead of Optional.or(null)");
        return mReference;
    }

    @Nullable
    @Override
    public T orNull() {
        return mReference;
    }
}
