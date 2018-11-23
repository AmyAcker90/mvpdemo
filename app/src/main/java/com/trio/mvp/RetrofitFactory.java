package com.trio.mvp;

import android.os.Parcelable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lixia on 2018/11/22.
 */

public class RetrofitFactory {
   private static Retrofit retrofit;
    private static Interceptor mInterceptor;

    public static RetrofitFactory getInstance() {
        return RetrofitFactoryHolder.mInstance;
    }

    private static class RetrofitFactoryHolder {
        private static final RetrofitFactory mInstance = new RetrofitFactory();
    }

    private static void init() {
        mInterceptor = chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build();
            return chain.proceed(request);
        };
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.135:8080/standard/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build();
    }

    private static OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
    }

    private static HttpLoggingInterceptor initLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


    public static <T> T create(Class<T> service) {
        init();
        return retrofit.create(service);
    }
}
