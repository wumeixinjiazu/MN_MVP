package com.videocomm.mn_mvp.ipInfo;

import com.videocomm.mn_mvp.model.IpInfo;
import com.videocomm.mn_mvp.net.LoadTasksCallBack;
import com.videocomm.mn_mvp.net.NetTask;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public class IpInfoPresenter implements IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    private NetTask netTask;
    private IpInfoContract.View addTaskView;
    private Subscription subscription;
    private CompositeSubscription mSubscriptions;

    public IpInfoPresenter(NetTask netTask, IpInfoContract.View addTaskView) {
        this.netTask = netTask;
        this.addTaskView = addTaskView;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void getInfo(String ip) {
        subscription = netTask.execute(ip, this);
        subscribe();
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

    @Override
    public void subscribe() {
        if (subscription != null) {
            mSubscriptions.add(subscription);
        }
    }

    @Override
    public void unsubscribe() {
        if (mSubscriptions != null && mSubscriptions.hasSubscriptions()) {
            mSubscriptions.unsubscribe();
        }
    }
}
