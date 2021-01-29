package com.videocomm.mn_mvp.ipInfo.dagger;

import com.videocomm.mn_mvp.ipInfo.MainActivity;
import com.videocomm.mn_mvp.net.dagger.NetTaskComponent;
import com.videocomm.mn_mvp.util.FragmentScoped;

import dagger.Component;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
@FragmentScoped
@Component(dependencies = NetTaskComponent.class, modules = IpInfoModule.class)
public interface IpInfoComponent {
    void inject(MainActivity ipInfoActivity);
}
