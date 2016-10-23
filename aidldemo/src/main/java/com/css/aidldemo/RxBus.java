package com.css.aidldemo;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by hexiaohong on 16/8/3.
 */
public final class RxBus {
//123123123jjjjj11```````````


    ///////55555555555555555

    //666666

    //7

    //8

    //9

    //10

    //11

    //12
    private static RxBus mInstance;

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }

        return mInstance;
    }

    //private final PublishSubject<Object> bus = PublishSubject.create();

    // If multiple threads are going to emit events to this
    // then it must be made thread-safe like this instead
    private final Subject<Object, Object> bus = new SerializedSubject<Object, Object>(PublishSubject.create());

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public <T> Observable<T> toObservable(Class<T> cls) {
        return bus.ofType(cls);
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

}
