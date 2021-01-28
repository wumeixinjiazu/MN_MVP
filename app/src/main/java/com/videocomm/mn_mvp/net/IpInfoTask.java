package com.videocomm.mn_mvp.net;

import com.videocomm.mn_mvp.model.IpInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public class IpInfoTask implements NetTask<String> {
    private static IpInfoTask INSTANCE = null;
    private static final String HOST = "https://v1.jinrishici.com/";
    private Retrofit retrofit;

    public IpInfoTask() {
        createRetrofit();
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder().
                baseUrl(HOST).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                build();
    }

    public static IpInfoTask getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IpInfoTask();
        }
        return INSTANCE;
    }

    @Override
    public Subscription execute(String data, LoadTasksCallBack loadTasksCallBack) {
        IpService ipService = retrofit.create(IpService.class);
        Subscription Subscription = ipService.getIpInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<IpInfo>() {
            @Override
            public void onStart() {
                loadTasksCallBack.onStart();
            }

            @Override
            public void onCompleted() {
                loadTasksCallBack.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                loadTasksCallBack.onFailed();
            }

            @Override
            public void onNext(IpInfo ipInfo) {
                loadTasksCallBack.onSuccess(ipInfo);
            }
        });

        return Subscription;
    }

//    @Override
//    public void execute(String ip, LoadTasksCallBack callBack) {
//        HttpRequest.post(HOST, new BaseHttpRequestCallback<IpInfo>() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                callBack.onStart();
//            }
//
//            @Override
//            protected void onSuccess(IpInfo ipInfo) {
//                super.onSuccess(ipInfo);
//                callBack.onSuccess(ipInfo);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                callBack.onFinish();
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//                callBack.onFailed();
//            }
//        });
//    }
}
