package com.videocomm.mn_mvp.ipInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

import com.videocomm.mn_mvp.MvpApplication;
import com.videocomm.mn_mvp.R;
import com.videocomm.mn_mvp.ipInfo.IpInfoFragment;
import com.videocomm.mn_mvp.ipInfo.IpInfoPresenter;
import com.videocomm.mn_mvp.ipInfo.dagger.DaggerIpInfoComponent;
import com.videocomm.mn_mvp.ipInfo.dagger.IpInfoModule;
import com.videocomm.mn_mvp.net.IpInfoTask;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
//    @Inject
//    private IpInfoPresenter ipInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IpInfoFragment ipInfoFragment = (IpInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (ipInfoFragment == null) {
            ipInfoFragment = IpInfoFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, ipInfoFragment).commit();
        }
//        IpInfoTask ipInfoTask = IpInfoTask.getInstance();
//        ipInfoPresenter = new IpInfoPresenter(ipInfoTask, ipInfoFragment);
//        ipInfoFragment.setPresenter(ipInfoPresenter);
        MvpApplication application = (MvpApplication) getApplication();
        DaggerIpInfoComponent.builder().ipInfoModule(new IpInfoModule(ipInfoFragment)).
                netTaskComponent(application.getTasksRepositoryComponent()).build().inject(this);



    }

}