robospice-fluent-request
========================

A fluent api wrapper for RoboSpice requests.

[RoboSpice](https://github.com/stephanenicolas/robospice) caches results for your requests if
a cache key is supplied to the [SpiceManager.execute()](http://stephanenicolas.github.io/robospice/site/latest/apidocs/com/octo/android/robospice/SpiceManager.html#execute%28com.octo.android.robospice.request.SpiceRequest, java.lang.Object, long, com.octo.android.robospice.request.listener.RequestListener%29)
method&mdash;every single time you submit a request.

A default cache key and cache expiry can be defined for each request type by implementing
[Cacheable](https://github.com/anotherdev/robospice-fluent-request/blob/master/library/src/main/java/com/anotherdev/android/robospice/request/Cacheable.java)
interface and submit the request using robospice-fluent-request.



Download
========

Gradle:
```groovy
compile 'com.anotherdev.android.robospice:robospice-fluent-request:0.1.0'
```


Usage
=====

Simple:
```java
RoboSpice.with(spiceManager).execute(spiceRequest);
RoboSpice.with(spiceManager).inform(requestListner).execute(spiceRequest);
```

Options:
Each option overrides the paramenter defined in the `spiceRequest` if the request implement
[Cacheable](https://github.com/anotherdev/robospice-fluent-request/blob/master/library/src/main/java/com/anotherdev/android/robospice/request/Cacheable.java).

```java
RoboSpice.with(spiceManager)
    .cache(keyString)
    .expiry(durationInMillis)
    .acceptDirtyCache(true)
    .retry(retryPolicy)
    .priority(PRIORITY_NORMAL)
    .inform(requestListener)
    .execute(spiceRequest);
```


License
--------

    Copyright 2015 anotherdev

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.