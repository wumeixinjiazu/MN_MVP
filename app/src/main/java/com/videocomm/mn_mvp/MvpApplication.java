package com.videocomm.mn_mvp;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.videocomm.mn_mvp.net.dagger.DaggerNetTaskComponent;
import com.videocomm.mn_mvp.net.dagger.NetTaskComponent;
import com.videocomm.mn_mvp.net.dagger.NetTaskModule;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public class MvpApplication extends Application {
    private NetTaskComponent netTaskComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());

        MultiDex.install(this);
        netTaskComponent = DaggerNetTaskComponent.builder().netTaskModule(new NetTaskModule()).build();
    }

    public NetTaskComponent getTasksRepositoryComponent() {
        return netTaskComponent;
    }
}
