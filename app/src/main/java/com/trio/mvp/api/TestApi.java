package com.trio.mvp.api;

import com.trio.mvp.base.BaseResp;
import com.trio.mvp.api.bean.ShowResp;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lixia on 2018/11/22.
 */

public interface TestApi {

    @GET("showInfo/queryList")
    Observable<BaseResp<List<ShowResp>>> queryShowList(@Query("index") int index,
                                                       @Query("pageSize") int pageSize);
}
