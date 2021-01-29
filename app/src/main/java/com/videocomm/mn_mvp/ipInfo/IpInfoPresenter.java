package com.videocomm.mn_mvp.ipInfo;

import com.videocomm.mn_mvp.model.IpInfo;
import com.videocomm.mn_mvp.net.LoadTasksCallBack;
import com.videocomm.mn_mvp.net.NetTask;

import javax.inject.Inject;

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


    /**
     * IpInfoPresenter的构造方法用@Inject标记，
     * 意味着Dagger2可以使用IpInfoPresenter构造方法来构建IpInfoPresenter。
     * @param netTask
     * @param addTaskView
     */
    @Inject
    public IpInfoPresenter(IpInfoContract.View addTaskView,NetTask netTask) {
        this.netTask = netTask;
        this.addTaskView = addTaskView;
        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    void setPresenter(){
        addTaskView.setPresenter(this);
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
