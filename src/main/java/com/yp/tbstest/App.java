package com.yp.tbstest;

import android.app.Application;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //增加这句话
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                System.out.println("oooyp==== 内核加载是否完成 " + b);
            }
        });


        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {

            }

            @Override
            public void onInstallFinish(int i) {

            }

            @Override
            public void onDownloadProgress(int i) {
                System.out.println("oooyp==== 内核下载进度为 " + i);
            }
        });
        QbSdk.setDownloadWithoutWifi(true);
    }
}
