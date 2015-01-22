package com.anotherdev.android.robospice.request;

public interface Cacheable {

    /**
     * Cache key for Robospice service. Don't use file system path separator character (e.g. "/").
     *
     * @return Cache key. Returning <tt>null</tt> disable caching for the request
     */
    String getCacheKey();

    long getCacheDurationInMillis();

    /**
     * Allow loading data from cache and notify listener first
     * then load from network if the cache is expired.
     */
    boolean isAcceptingDirtyCache();
}
