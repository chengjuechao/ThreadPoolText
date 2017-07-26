package com.chengjuechao.threadpooltext;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_a, tv_b, tv_c, tv_d, tv_e;
    private Button btn_a, btn_b, btn_c, btn_d, btn_e, btn_all;
    private ProgressBar pb_a, pb_b, pb_c, pb_d, pb_e;
    private ExecutorService mCachedThreadPool, mFixedThreadPool, mSingleThreadPool;
    private ScheduledExecutorService mScheduledThreadPool;
    private static final int MAX_PROGRESS = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mCachedThreadPool = Executors.newCachedThreadPool();//创建可缓存线程池
        mFixedThreadPool = Executors.newFixedThreadPool(3);//创建定长线程池
        mScheduledThreadPool = Executors.newScheduledThreadPool(3);
        mSingleThreadPool = Executors.newSingleThreadExecutor();
    }

    private void startDownload(final ProgressBar progressBar, final int i) {
       /* mScheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                progressBar.setMax(MAX_PROGRESS);
                while (p < MAX_PROGRESS) {
                    p++;
                    progressBar.setProgress(p);
                    Bundle bundle = new Bundle();
                    Message message = new Message();
                    bundle.putInt("p", p);
                    bundle.putString("ThreadName", Thread.currentThread().getName());
                    message.what = i;
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },3, 7, TimeUnit.SECONDS);*/
        mScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                progressBar.setMax(MAX_PROGRESS);
                while (p < MAX_PROGRESS) {
                    p++;
                    progressBar.setProgress(p);
                    Bundle bundle = new Bundle();
                    Message message = new Message();
                    bundle.putInt("p", p);
                    bundle.putString("ThreadName", Thread.currentThread().getName());
                    message.what = i;
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },3, 7, TimeUnit.SECONDS);
       /* mScheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                progressBar.setMax(MAX_PROGRESS);
                while (p < MAX_PROGRESS) {
                    p++;
                    progressBar.setProgress(p);
                    Bundle bundle = new Bundle();
                    Message message = new Message();
                    bundle.putInt("p", p);
                    bundle.putString("ThreadName", Thread.currentThread().getName());
                    message.what = i;
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 3, TimeUnit.SECONDS);*/
        /*mScheduledThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                progressBar.setMax(MAX_PROGRESS);
                while (p < MAX_PROGRESS) {
                    p++;
                    progressBar.setProgress(p);
                    Bundle bundle = new Bundle();
                    Message message = new Message();
                    bundle.putInt("p", p);
                    bundle.putString("ThreadName", String.valueOf(Thread.currentThread().getId()));
                    message.what = i;
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
    }

    private void initView() {
        tv_a = (TextView) findViewById(R.id.tv_a);
        tv_b = (TextView) findViewById(R.id.tv_b);
        tv_c = (TextView) findViewById(R.id.tv_c);
        tv_d = (TextView) findViewById(R.id.tv_d);
        tv_e = (TextView) findViewById(R.id.tv_e);
        btn_a = (Button) findViewById(R.id.btn_a);
        btn_b = (Button) findViewById(R.id.btn_b);
        btn_c = (Button) findViewById(R.id.btn_c);
        btn_d = (Button) findViewById(R.id.btn_d);
        btn_e = (Button) findViewById(R.id.btn_e);
        btn_all = (Button) findViewById(R.id.btn_all);
        pb_a = (ProgressBar) findViewById(R.id.progressBar);
        pb_b = (ProgressBar) findViewById(R.id.progressBar1);
        pb_c = (ProgressBar) findViewById(R.id.progressBar2);
        pb_d = (ProgressBar) findViewById(R.id.progressBar3);
        pb_e = (ProgressBar) findViewById(R.id.progressBar4);
        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_d.setOnClickListener(this);
        btn_e.setOnClickListener(this);
        btn_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_a:
                startDownload(pb_a, 1);
                break;
            case R.id.btn_b:
                startDownload(pb_b, 2);
                break;
            case R.id.btn_c:
                startDownload(pb_c, 3);
                break;
            case R.id.btn_d:
                startDownload(pb_d, 4);
                break;
            case R.id.btn_e:
                startDownload(pb_e, 5);
                break;
            case R.id.btn_all:
                startDownload(pb_a, 1);
                startDownload(pb_b, 2);
                startDownload(pb_c, 3);
                startDownload(pb_d, 4);
                startDownload(pb_e, 5);
                break;

        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.getData().getInt("p");
            String ThreadName = msg.getData().getString("ThreadName");
            switch (msg.what) {
                case 1:
                    tv_a.setText("已下载" + i + "/" + MAX_PROGRESS + "Thread:" + ThreadName);
                    break;
                case 2:
                    tv_b.setText("已下载" + i + "/" + MAX_PROGRESS + "Thread:" + ThreadName);
                    break;
                case 3:
                    tv_c.setText("已下载" + i + "/" + MAX_PROGRESS + "Thread:" + ThreadName);
                    break;
                case 4:
                    tv_d.setText("已下载" + i + "/" + MAX_PROGRESS + "Thread:" + ThreadName);
                    break;
                case 5:
                    tv_e.setText("已下载" + i + "/" + MAX_PROGRESS + "Thread:" + ThreadName);
                    break;
            }
        }
    };
}
