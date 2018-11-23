package com.trio.mvp.test;

import com.trio.mvp.base.BaseView;
import com.trio.mvp.local.ShowInfo;

import java.util.List;

/**
 * Created by lixia on 2018/11/22.
 */

interface TestView extends BaseView {

    void showData(List<ShowInfo> showInfos);

}
