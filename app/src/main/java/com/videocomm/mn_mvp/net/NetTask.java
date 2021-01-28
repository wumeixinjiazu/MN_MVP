package com.videocomm.mn_mvp.net;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public interface NetTask<T> {
    void execute(T data,LoadTasksCallBack callBack);
}
