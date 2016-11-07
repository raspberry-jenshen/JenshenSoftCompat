package com.jenshen.compat.base.view.impl.mvp.lce;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.jenshen.compat.base.view.BaseLceMvpView;
import com.jenshen.compat.util.delegate.ViewDelegateActivity;


public abstract class BaseLceMvpActivity<CV extends View, M, V extends BaseLceMvpView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<CV, M, V, P>
        implements BaseLceMvpView<M> {

    @Nullable
    private ViewDelegateActivity viewDelegate;

    /**
     * invoke this method on child constructor if you want to customise a delegate
     *
     * @param viewDelegate
     */
    public void setViewDelegate(@NonNull ViewDelegateActivity viewDelegate) {
        this.viewDelegate = viewDelegate;
    }


    /* view */

    @Override
    public void handleError(Throwable throwable) {
        createDelegateIfNull().handleError(throwable);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return createDelegateIfNull().getErrorMessage(e);
    }


    /* lifecycle */

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        createDelegateIfNull().onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        createDelegateIfNull().onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDelegateIfNull().onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createDelegateIfNull().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        createDelegateIfNull().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        createDelegateIfNull().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createDelegateIfNull().onDestroy();
    }

    /* private methods */

    private ViewDelegateActivity createDelegateIfNull() {
        if (viewDelegate == null) {
            viewDelegate = new ViewDelegateActivity(this);
        }
        return viewDelegate;
    }
}
