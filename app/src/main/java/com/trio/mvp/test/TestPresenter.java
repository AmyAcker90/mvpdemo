package com.trio.mvp.test;

import com.trio.mvp.RetrofitFactory;
import com.trio.mvp.api.TestApi;
import com.trio.mvp.api.bean.ShowResp;
import com.trio.mvp.base.BasePresenter;
import com.trio.mvp.base.BaseResp;
import com.trio.mvp.base.BaseView;
import com.trio.mvp.local.ShowInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lixia on 2018/11/22.
 */

public class TestPresenter implements BasePresenter {
    private TestView mView;

    public TestPresenter(TestView testView) {
        mView = testView;
    }

    @Override
    public void loadData(boolean isRefresh) {
        RetrofitFactory.create(TestApi.class)
                .queryShowList(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<List<ShowResp>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(500, e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResp<List<ShowResp>> listBaseResp) {
                        if (listBaseResp.getRet() == 200) {
                            List<ShowInfo> list = new ArrayList<>();
                            for (ShowResp showResp : listBaseResp.getData()) {
                                ShowInfo showInfo = new ShowInfo();
                                showInfo.setDesc(showResp.getDesc());
                                list.add(showInfo);
                            }
                            mView.showData(list);
                        } else
                            mView.onError(listBaseResp.getRet(), listBaseResp.getMsg());
                    }
                });
    }
}
