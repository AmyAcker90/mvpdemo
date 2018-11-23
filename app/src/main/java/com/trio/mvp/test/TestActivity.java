package com.trio.mvp.test;

import android.os.Bundle;
import android.widget.Toast;

import com.trio.mvp.R;
import com.trio.mvp.base.BaseActivity;
import com.trio.mvp.local.ShowInfo;

import java.util.List;

public class TestActivity extends BaseActivity<TestPresenter> implements TestView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mPresenter = new TestPresenter(this);
        mPresenter.loadData(false);
    }

    @Override
    public void showData(List<ShowInfo> showInfos) {

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        System.out.println("Thread: " + Thread.currentThread().getId());
        Toast.makeText(this, "获取成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int errorCode, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
