package com.videocomm.mn_mvp.net;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
@Module
public class NetTaskModule {

    @Singleton
    @Provides
    NetTask provideIpInfoTask() {
        return new IpInfoTask();
    }
}
