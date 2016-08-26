package com.atguigu.mycyc.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.mycyc.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 专门提供为处理一些UI相关的问题而创建的工具类
 */
public class UIUtils{

    public static Context getContext(){
        return MyApplication.context;
    }

    public static Handler getHandler(){
        return MyApplication.handler;
    }

    public static int getColor(int colorId){
        return getContext().getResources().getColor(colorId);
    }

    public static View getXmlView(int viewId){
        return View.inflate(getContext(), viewId, null);
    }

    public static String[] getStringArray(int arrayId){
        return getContext().getResources().getStringArray(arrayId);
    }

    //将dp转化为px
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;

        return (int)(density * dp + 0.5);//四舍五入
    }

    //将px转化为dp
    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;

        return (int)(px / density + 0.5);//四舍五入
    }
    //将方法中要执行的操作在主线程中执行
    public static void runOnUiThread(Runnable runnable){
        if(isMainThread()){
            runnable.run();
        }else{
            //通过发送消息，使得如下的操作在主线程中执行
            getHandler().post(runnable);
        }
    }

    //判断当前线程是否是主线程
    private static boolean isMainThread() {
        int tid = android.os.Process.myTid();//得到当前的线程id
        return tid == MyApplication.mainThreadId;

    }

    //toast显示
    public static void toast(String message, boolean b) {
        Toast.makeText(getContext(), message,b? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
    public static void quickTime(long time, final TextView view) {
        CountDownTimer timer = new CountDownTimer(time, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String date = sdf.format(new Date(millisUntilFinished));
                view.setText(date);
            }

            @Override
            public void onFinish() {
                view.setText("00:00:00");
            }
        }.start();
    }

}
