package com.videocomm.mn_mvp.net.dagger;

import com.videocomm.mn_mvp.net.NetTask;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
@Singleton
@Component(modules =NetTaskModule.class)
public interface NetTaskComponent {
    NetTask getNetTask();
}
