package com.atguigu.mycyc.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/23 on 18:05.
 * 作用:
 */
public class CacheUtils {
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
    public static void putString(Context context, String key, String result){
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putString(key, result).commit();
    }
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
    //读取本地字符创集合文件
    public static void readTxtFile(String filePath,List<String> list) {
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuffer sBuffer = new StringBuffer("");
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //System.out.println(lineTxt);
                    sBuffer.append(lineTxt);
                    sBuffer.append("\n");
                }
                list.add(sBuffer.toString());
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        System.out.println("listlist--->>>" + list);
    }
    //保存字符创集合到本地
    public static void writeTxtFile(String filePath,List<String> list) {
           if(list!=null&&list.size()>0) {
               File file=new File(filePath);
               try {
                   PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                   for(int i = 0; i < list.size(); i++) {
                     pw.print(list.get(i));
                   }
                   pw.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }

    }

}
