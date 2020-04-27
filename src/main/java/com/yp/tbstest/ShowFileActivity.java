package com.yp.tbstest;

import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

public class ShowFileActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback{

    private final String TAG = "ShowFileActivity";

    private RelativeLayout rl;

    private TbsReaderView tbsReaderView;

    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_file);
        rl = findViewById(R.id.rl);
        tbsReaderView = new TbsReaderView(this,this);
        rl.addView(tbsReaderView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        filePath = getIntent().getStringExtra("fileName");

        File fileName = new File(filePath);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.d("#####","filePath="+getExternalFilesDirs(Environment.MEDIA_MOUNTED)[0].getAbsolutePath());
        }

        Bundle bundle = new Bundle();
        bundle.putString("filePath","/storage/emulated/0/test.pdf");
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());

        boolean bool = tbsReaderView.preOpen(getFileType("/storage/emulated/0/test.pdf"),false);
        if(bool){
            tbsReaderView.openFile(bundle);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tbsReaderView.onStop();
    }

    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            Log.d(TAG, "paramString---->null");
            return str;
        }
        Log.d(TAG, "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Log.d(TAG, "i <= -1");
            return str;
        }


        str = paramString.substring(i + 1);
        Log.d(TAG, "paramString.substring(i + 1)------>" + str);
        return str;
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }
}
