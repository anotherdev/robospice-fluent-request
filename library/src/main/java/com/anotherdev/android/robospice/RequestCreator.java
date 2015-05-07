package com.anotherdev.android.robospice;

import com.anotherdev.android.robospice.request.Cacheable;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.retry.RetryPolicy;

import javax.annotation.Nullable;

public class RequestCreator {

    private final SpiceManager mManager;

    private Optional<String> mCacheKey = Optional.absent();
    private Optional<Long> mCacheExpiry = Optional.absent();
    private Optional<Boolean> mAcceptDirtyCache = Optional.absent();
    private Optional<RetryPolicy> mRetryPolicy = Optional.absent();
    private Optional<Integer> mPriority = Optional.absent();


    RequestCreator(SpiceManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("SpiceManager must not be null");
        }
        mManager = manager;
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

    public RequestCreator priority(int priority) {
        mPriority = Optional.from(priority);
        return this;
    }

    public <T> RequestExecutor<T> inform(RequestListener<T> listener) {
        return new RequestExecutor<>(this, listener);
    }

    public <T> void execute(SpiceRequest<T> request) {
        execute(request, null);
    }

    <T> void execute(SpiceRequest<T> request, RequestListener<T> listener) {
        if (request == null) {
            throw new IllegalArgumentException("SpiceRequest must not be null");
        }

        Optional<String> requestKey = Optional.absent();
        Optional<Long> requestExpiry = Optional.absent();
        Optional<Boolean> requestAcceptDirty = Optional.absent();

        if (request instanceof Cacheable) {
            Cacheable cacheable = (Cacheable) request;
            requestKey = Optional.from(cacheable.getCacheKey());
            requestExpiry = Optional.from(cacheable.getCacheDurationInMillis());
            requestAcceptDirty = Optional.from(cacheable.isAcceptingDirtyCache());
        }

        if (mRetryPolicy.isPresent()) {
            // Override RetryPolicy
            request.setRetryPolicy(mRetryPolicy.get());
        }

        if (mPriority.isPresent()) {
            // Override priority
            request.setPriority(mPriority.get());
        }

        final String key = mCacheKey.or(requestKey).orNull();
        final long expiry = mCacheExpiry.or(requestExpiry).or(DurationInMillis.ALWAYS_EXPIRED);
        final boolean acceptDirtyCache = mAcceptDirtyCache.or(requestAcceptDirty).or(false);

        if (acceptDirtyCache) {
            mManager.getFromCacheAndLoadFromNetworkIfExpired(request, key, expiry, listener);
        } else {
            mManager.execute(request, key, expiry, listener);
        }
    }
}
