package com.yp.tbstest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.sdk.WebView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();
        final WebView webView = findViewById(R.id.web_view);

        if (webView.getX5WebViewExtension() == null){
            Log.d("######","没有加载内核");
        }

        findViewById(R.id.read_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ShowFileActivity.class);
                intent.putExtra("fileName",Environment.getExternalStorageDirectory().getPath()+"/test.xlsx");
                System.out.println("oooyp==== path ----- " + Environment.getExternalStorageDirectory().getPath()+"/test.xlsx");
//                System.out.println("oooyp==== path ----- " + getApplication().getDir("test", Context.MODE_PRIVATE).getAbsoluteFile()+"/test.docx");
//                intent.putExtra("fileName",getApplication().getDir("test", Context.MODE_PRIVATE).getAbsoluteFile()+"/test.docx");
//                intent.putExtra("fileName",Environment.getExternalStorageDirectory().getPath()+"/test/Kotlin从零到精通Android.pdf");
                startActivity(intent);
            }
        });

        findViewById(R.id.load_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://debugtbs.qq.com");
            }
        });
    }

    private void requestPermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
