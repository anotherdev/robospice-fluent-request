package com.anotherdev.android.robospice;

import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.annotation.Nullable;

public class RequestExecutor<T> {

    private final RequestCreator mCreator;

    private Optional<RequestListener<T>> mListener = Optional.absent();


    RequestExecutor(RequestCreator creator, @Nullable RequestListener<T> listener) {
        mCreator = creator;
        mListener = Optional.from(listener);
    }

    public void execute(SpiceRequest<T> request) {
        mCreator.execute(request, mListener.orNull());
    }
}
