package com.anotherdev.android.robospice;

import javax.annotation.Nullable;

abstract class Optional<T> {

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> from(@Nullable T nullableReference) {
        return (nullableReference == null)
                ? Optional.<T>absent()
                : new Present<>(nullableReference);
    }


    Optional() {}

    public abstract boolean isPresent();

    public abstract T get();

    public abstract T or(T defaultValue);

    public abstract Optional<T> or(Optional<? extends T> secondChoice);

    @Nullable
    public abstract T orNull();


    protected static <R> R checkNotNull(R reference) {
        return checkNotNull(reference, null);
    }

    protected static <R> R checkNotNull(R reference, @Nullable CharSequence errorMessage) {
        if (reference == null) {
            if (errorMessage == null) {
                throw new NullPointerException();
            } else {
                throw new NullPointerException(String.valueOf(errorMessage));
            }
        }
        return reference;
    }
}
