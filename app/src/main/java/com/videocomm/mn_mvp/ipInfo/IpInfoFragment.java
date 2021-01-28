package com.videocomm.mn_mvp.ipInfo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.videocomm.mn_mvp.R;
import com.videocomm.mn_mvp.model.IpData;
import com.videocomm.mn_mvp.model.IpInfo;

/**
 * @author[]
 * @version[创建日期，2021/1/28]
 * @function[功能简介 ]
 **/
public class IpInfoFragment extends Fragment implements IpInfoContract.View {
    private TextView tv_country;
    private TextView tv_area;
    private TextView tv_city;
    private Button bt_ipinfo;
    private Dialog mDialog;
    private IpInfoContract.Presenter mPresenter;

    public static IpInfoFragment newInstance() {
        return new IpInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ipinfo, container, false);
        tv_country = (TextView) root.findViewById(R.id.tv_country);
        tv_area = (TextView) root.findViewById(R.id.tv_area);
        tv_city = (TextView) root.findViewById(R.id.tv_city);
        bt_ipinfo = (Button) root.findViewById(R.id.bt_ipinfo);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = new ProgressDialog(getActivity());
        mDialog.setTitle("获取数据中");
        bt_ipinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getInfo("39.155.184.147");
            }
        });
    }

    @Override
    public void setIpInfo(IpInfo ipInfo) {
        if (ipInfo != null) {
            tv_country.setText(ipInfo.getAuthor());
            tv_area.setText(ipInfo.getCategory());
            tv_city.setText(ipInfo.getContent());
        }
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "网络出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消网络请求
        mPresenter.unsubscribe();
    }
}
