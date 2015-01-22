package com.anotherdev.android.robospice;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.retry.RetryPolicy;

import javax.annotation.Nullable;

public class RequestCreator {

    private final SpiceManager mSpiceManager;

    private Optional<String> mCacheKey = Optional.absent();
    private Optional<Long> mCacheExpiry = Optional.absent();
    private Optional<Boolean> mAcceptDirtyCache = Optional.absent();
    private Optional<RetryPolicy> mRetryPolicy = Optional.absent();


    RequestCreator(SpiceManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("SpiceManager must not be null");
        }
        mSpiceManager = manager;
    }

    public RequestCreator cache(@Nullable String key) {
        mCacheKey = Optional.from(key);
        return this;
    }

    public RequestCreator expiry(long durationInMillis) {
        mCacheExpiry = Optional.from(durationInMillis);
        return this;
    }

    public RequestCreator acceptDirtyCache(boolean enable) {
        mAcceptDirtyCache = Optional.from(enable);
        return this;
    }

    public RequestCreator retry(RetryPolicy retryPolicy) {
        mRetryPolicy = Optional.from(retryPolicy);
        return this;
    }

    public <T> RequestExecutor<T> notify(RequestListener<T> listener) {
        return new RequestExecutor<>(this, listener);
    }

    public <T> void execute(SpiceRequest<T> request) {
        execute(request, null);
    }

    <T> void execute(SpiceRequest<T> request, RequestListener<T> listener) {
        // TODO hand off to SpiceManager
    }
}
