package com.example.personal_library.glide;

import com.bumptech.glide.Glide;

/**
 * Created by wxl on 2017/12/26.
 */

public class RxGlideutils {

    public static void loadingimage() {
        String Url = "http://218.192.170.132/1.jpg";

//Glide使用了流式接口的调用方式
//Glide类是核心实现类。
//        Glide.with(context).load(Url).into(targetImageView);

//        设置加载尺寸
//        Glide.with(this).load(imageUrl).override(800, 800).into(imageView);

//        设置加载中以及加载失败图片
//        api里面对placeholder()、error()函数中有多态实现，用的时候可以具体的熟悉一下
//                Glide
//                .with(this)
//                .load(imageUrl)
//                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);

//        设置加载动画
//        Glide.with(this).load(imageUrl).animate(R.anim.item_alpha_in).into(imageView);
//        api也提供了几个常用的动画：比如crossFade()
//        R.anim.item_alpha_in
//
//                <? xml version = "1.0" encoding = "utf-8" ?>
//                    <set xmlns:android = "http://schemas.android.com/apk/res/android" >
//    <alpha
//        android:
//        duration = "500"
//        android:
//        fromAlpha = "0.0"
//        android:
//        toAlpha = "1.0" / >
//</set >
//

//        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//
//// 缓存参数说明
//// DiskCacheStrategy.NONE：不缓存任何图片，即禁用磁盘缓存
//// DiskCacheStrategy.ALL ：缓存原始图片 & 转换后的图片（默认）
//// DiskCacheStrategy.SOURCE：只缓存原始图片（原来的全分辨率的图像，即不缓存转换后的图片）
//// DiskCacheStrategy.RESULT：只缓存转换后的图片（即最终的图像：降低分辨率后 / 或者转换后 ，不缓存原始图片

//        清理缓存
//        Glide.get(this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
//        Glide.get(this).clearMemory();//清理内存缓存 可以在UI主线程中进行

    }

    ;
}
