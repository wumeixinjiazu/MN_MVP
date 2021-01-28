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

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public class IpInfoTask implements NetTask<String> {
    private static IpInfoTask INSTANCE = null;
    private static final String HOST = "https://v1.jinrishici.com/all.json";

    private IpInfoTask() {

    }

    public static IpInfoTask getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IpInfoTask();
        }
        return INSTANCE;
    }

    @Override
    public void execute(String ip, LoadTasksCallBack callBack) {
//        RequestParams requestParams = new RequestParams();
//        requestParams.addFormDataPart("ip", ip);
        HttpRequest.post(HOST, new BaseHttpRequestCallback<IpInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            protected void onSuccess(IpInfo ipInfo) {
                super.onSuccess(ipInfo);
                callBack.onSuccess(ipInfo);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                callBack.onFinish();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                callBack.onFailed();
            }
        });
    }
}
