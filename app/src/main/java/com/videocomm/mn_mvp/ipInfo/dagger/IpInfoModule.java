package com.videocomm.mn_mvp.ipInfo.dagger;

import com.videocomm.mn_mvp.ipInfo.IpInfoContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
@Module
public class IpInfoModule {
    private IpInfoContract.View mView;
    public IpInfoModule(IpInfoContract.View mView){

        this.mView=mView;
    }
    @Provides
    IpInfoContract.View provideIpInfoContract() {
        return mView;
    }
}
