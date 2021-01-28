package com.videocomm.mn_mvp.net;

import com.videocomm.mn_mvp.model.IpInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public interface IpService {

    @POST("all.json")
    Observable<IpInfo> getIpInfo();
}
