package com.anotherdev.android.robospice.rx;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxRequestListener<T> implements RequestListener<T> {

    private final PublishSubject<T> mSubject = PublishSubject.create();


    @Override
    public void onRequestSuccess(T result) {
        mSubject.onNext(result);
    }

    @Override
    public void onRequestFailure(SpiceException e) {
        mSubject.onError(e);
    }

    public Observable<T> asObservable() {
        return mSubject;
    }
}
