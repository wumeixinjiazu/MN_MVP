package com.videocomm.mn_mvp.ipInfo;

import com.videocomm.mn_mvp.BasePresenter;
import com.videocomm.mn_mvp.BaseView;
import com.videocomm.mn_mvp.model.IpInfo;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public interface IpInfoContract {
    interface Presenter extends BasePresenter {
        void getInfo(String ip);
    }

    interface View extends BaseView<Presenter> {
        void setIpInfo(IpInfo ipInfo);

        void showLoading();

        void hideLoading();

        void showError();

        boolean isActive();
    }
}
