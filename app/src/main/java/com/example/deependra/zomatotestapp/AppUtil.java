package com.example.deependra.zomatotestapp;

import java.util.Collection;

import io.reactivex.disposables.Disposable;

class AppUtil {
    static boolean isEmptyCollection(Collection collection) {
        return collection == null || collection.isEmpty();
    }
    static void disposeDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
