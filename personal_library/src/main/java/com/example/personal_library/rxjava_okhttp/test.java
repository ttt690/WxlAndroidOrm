package com.example.personal_library.rxjava_okhttp;


import android.util.Log;

import com.example.personal_library.rxjava_permission.WxlRxUtils;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wxl on 2017/12/25.
 */

public class test {

    public static final String POST_URL2 = "http://admin.wap.china.com/user/NavigateTypeAction.do?processID=getNavigateNews";
    //    请求参数：page=1&code=news&pageSize=20&parentid=0&type=1



    public static String url = "http://mobile.weather.com.cn/data/forecast/101010100.html?_=1381891660081";
    public static void main(String[] args) {
//        get();
//        post();
//        postfile();
        WxlRxUtils.flatmap();

    }

    private static void postfile() {

    }

    private static void post() {
        //    请求参数：page=1&code=news&pageSize=20&parentid=0&type=1
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("page", "1")
                .add("code", "news")
                .add("pageSize", "20")
                .add("parentid", "0")
                .add("type", "1")
                .build();
        HttpUtils.doPost(POST_URL2,
                requestBodyPost )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("*********","result:"+s);
                        System.out.print(s);
                    }
                });
    }

    private static void get() {
        HttpUtils.doGet("http://www.weather.com.cn/data/cityinfo/101010100.html")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("*********","result:"+s);
                        System.out.print(s);
                    }
                });
        //    请求参数：page=1&code=news&pageSize=20&parentid=0&type=1
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("page", "1")
                .add("code", "news")
                .add("pageSize", "20")
                .add("parentid", "0")
                .add("type", "1")
                .build();
    }
}
