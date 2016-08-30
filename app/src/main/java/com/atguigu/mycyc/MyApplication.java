package com.atguigu.mycyc;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by shkstart on 2016/8/12 0012.
 */
public class MyApplication extends Application {

    public static Context context;
    public static Handler handler;
    public static Thread mainThread;//主线程
    public static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();//获取的是当前MyApplication的实例
        handler = new Handler();
        mainThread = Thread.currentThread();//当前实例化MyApplication的线程，就是主线程
        mainThreadId = android.os.Process.myTid();//获取当前线程的id

        //初始化未捕获异常处理方式的类的实例
//        CrashHandler.getInstance().init(this);
        //初始化okhttputils
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

}
