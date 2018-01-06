package com.example.personal_library.rxjava_okhttp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

public class HttpUtils {

    private static OkHttpClient client = null;
    public static final String TYPE = "application/octet-stream";
    private HttpUtils() {}

    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (HttpUtils.class) {
                if (client == null)
                    client = new OkHttpClient();
            }
        }
        return client;
    }

    /**
     * Get请求
     *
     * @param url
     * @param
     */
    public static Observable doGet(final String url) {

        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = getInstance().newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        subscriber.onNext(response.body().string());
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    /**
     * Post请求发送键值对数据
     *
     * @param url
     * @param
     * @param
     */
    public static Observable doPost(final String url, final RequestBody requestBody ) {

        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Call call = getInstance().newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        subscriber.onNext(response.body().string());
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    /**
     * Post请求发送JSON数据
     *
     * @param url
     * @param jsonParams
     * @param callback
     */
    public static void doPost(String url, String jsonParams, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * 上传文件
     *
     * @param url     * @param pathName
     * @param
     * @param
     */
    public static Observable doFile(final String url, final String pathName, final Context context) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
               postfile(  subscriber,  context);
            }
        });


    }

    private static void postfile(Subscriber subscriber, Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), "dd.mp4");
        if (!file.exists()) {

            Log.e("***********","文件不存在:"+file.getName().toString());
        } else {
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE), file);
            RequestBody requestBody = new MultipartBody.Builder().addFormDataPart("filename", file.getName(), fileBody).build();

            Request requestPostFile = new Request.Builder()
                    .url("http://10.11.64.50/upload/UploadServlet")
                    .post(requestBody)
                    .build();
            client.newCall(requestPostFile).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("***********","onFailure:");
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                  Log.e("***********","onResponse:"+response.body().string());
                }
            });
        }
    }



}
