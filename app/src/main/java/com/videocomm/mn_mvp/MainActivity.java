package com.videocomm.mn_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.videocomm.mn_mvp.ipInfo.IpInfoFragment;
import com.videocomm.mn_mvp.ipInfo.IpInfoPresenter;
import com.videocomm.mn_mvp.net.IpInfoTask;

public class MainActivity extends AppCompatActivity {
    private IpInfoPresenter ipInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IpInfoFragment ipInfoFragment = (IpInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (ipInfoFragment == null) {
            ipInfoFragment = IpInfoFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, ipInfoFragment).commit();
        }
        IpInfoTask ipInfoTask = IpInfoTask.getInstance();
        ipInfoPresenter = new IpInfoPresenter(ipInfoTask, ipInfoFragment);
        ipInfoFragment.setPresenter(ipInfoPresenter);
    }


}