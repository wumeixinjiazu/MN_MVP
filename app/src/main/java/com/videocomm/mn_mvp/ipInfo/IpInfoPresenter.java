package com.videocomm.mn_mvp.ipInfo;

import com.videocomm.mn_mvp.model.IpInfo;
import com.videocomm.mn_mvp.net.LoadTasksCallBack;
import com.videocomm.mn_mvp.net.NetTask;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public class IpInfoPresenter implements IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    private NetTask netTask;
    private IpInfoContract.View addTaskView;

    public IpInfoPresenter(NetTask netTask, IpInfoContract.View addTaskView) {
        this.netTask = netTask;
        this.addTaskView = addTaskView;
    }

    @Override
    public void getInfo(String ip) {
        netTask.execute(ip, this);
    }

    @Override
    public void onSuccess(IpInfo ipInfo) {
        if (addTaskView.isActive()) {
            addTaskView.setIpInfo(ipInfo);
        }
    }

    @Override
    public void onStart() {
        if (addTaskView.isActive()) {
            addTaskView.showLoading();
        }
    }

    @Override
    public void onFailed() {
        if (addTaskView.isActive()) {
            addTaskView.showError();
            addTaskView.hideLoading();
        }
    }

    @Override
    public void onFinish() {
        if (addTaskView.isActive()) {
            addTaskView.hideLoading();
        }
    }
}
