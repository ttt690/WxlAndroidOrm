package com.example.personal_library.rxjava_permission;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wxl on 2017/12/14.
 */

public   class WxlRxUtils {



    /**
     * 获取一或多个权限
     * @return
     */
    public  static  Observable getpermission(final Activity context, int code, final String[]... permission){
        return  Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                WxlPermission.getOnePermission(context,subscriber,100,permission);

            }
        });


    }

    /**
     * 定时器操作
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    public  static void timerObservable() {
        // 指定一定时间后才发射
        Observable.timer(4, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("ddddddddddddddddd timer along = " + aLong);
            }
        });
    }

    /**
     * 轮询操作
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    public  static void intervalObservable() {
        // 指定轮询时间(第一个参数)为x,轮询时间单位（第二个参数）为n开始轮询处理事件.
        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("ddddddddddddddddd interval aLong = " + aLong);
            }
        });
    }

    /**
     * 范围操作
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    public  static void rangeObservable() {
        // 从指定的数字x开始发射n个数字
        Observable.range(15, 5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("ddddddddddddddddd range integer = " + integer);
            }
        });
    }

    /**
     * 集合操作
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    public  static void fromObservable() {
        List<String> fromList = new ArrayList<String>();
        fromList.add("1");
        fromList.add("2");
        fromList.add("3");
        Observable.from(fromList).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("ddddddddddddddd from s = " + s);
            }
        });
    }

    /**
     * 重复操作
     * @author leibing
     * @createTime 2016/09/08
     * @lastModify 2016/09/08
     * @param
     * @return
     */
    public  static void repeatObservable(){
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Observable.from(integers).repeat(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("ddddddddddddddddddd repeat integer = " + integer);
            }
        });
    }

    //just 发送9个 以内参数

    public static  void justObservable(){

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.e("just_call", "number:" + number);
                    }
                });
    }
    //map  异步方法有返回值

    public  static void mapObservable(){
        Observable.just("images/logo.png") // 输入类型 String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
                        return null;
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
//                        showBitmap(bitmap);
                    }
                });
    }

    //flatmap 多步操作数据 返回被观察者 继续操作
    //  执行顺序 flatmap先遍历一遍 在分别map onnext  map onnext.....
    public static void flatmap(){

        ArrayList <String>list=new ArrayList<String>();
        ArrayList <String>list2=new ArrayList<String>();
        list.add("123");list.add("333");list.add("4444");list.add("111");
        Observable.from(list)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s) {

                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {

                                Log.e("********",s+"111");
                                subscriber.onNext(s);
                            }
                        });
                    }
                })
                .map(new Func1<String,String>() {
                    @Override
                    public String call(String s) {
                        Log.e("********",s+"222");
                        return s;
                    }
                })
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("********onNext", s);
            }
        })
        ;

    }

}
