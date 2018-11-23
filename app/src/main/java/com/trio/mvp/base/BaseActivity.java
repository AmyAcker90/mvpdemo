package com.trio.mvp.base;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by lixia on 2018/11/21.
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    protected T mPresenter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(int errorCode, String msg) {

    }
}
