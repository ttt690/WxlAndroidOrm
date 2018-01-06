package com.example.personal_library.rxjava_permission;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/6/20 0020.
 */
public class WxlPermission {
    private static WxlPermission wxlPermission;
    private static Activity contexts;

     private WxlPermission() {

     }

    static String[] concat(String[] a, String[] b) {
        String[] c= new String[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     *
     * @context         当前Activity
     * @permission      需要申请的权限
     * @code            对应权限code
     * @Handler         获取权限成功的回调
     * @return
     */
    public   static  WxlPermission  getOnePermission (Activity context, final Subscriber subscriber, int code, String[]... permission){
        contexts=context;
        if(wxlPermission==null){
            wxlPermission=new WxlPermission();
        }
        String[]resultarr=null;
        for(int i=0;i<permission.length;i++){
            if(i==0){
                resultarr=permission[0];
            }else{
                resultarr= concat(resultarr,permission[i]);
            }

        }
        Log.e("*********","resultarr="+resultarr);

        PermissionListener permissionListener = new PermissionListener() {

            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                switch (requestCode) {
                    case 100: {
                        Log.e("[[[66","onSucceed");

                        subscriber.onNext(true);
                        subscriber.onCompleted();
                        break;
                    }

                }
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                switch (requestCode) {
                    case 100: {
                        Log.e("[[[[[[","onFailed");

                        break;
                    }

                }

                // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
                if (AndPermission.hasAlwaysDeniedPermission(contexts, deniedPermissions)) {
                    // 第一种：用默认的提示语。
//                AndPermission.defaultSettingDialog(MainActivity.this, 300).show();

                    // 第二种：用自定义的提示语。
                    AndPermission.defaultSettingDialog(contexts, 300)
                            .setTitle("权限申请失败")
                            .setMessage("一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                            .setPositiveButton("去设置")
                            .show();

//            第三种：自定义dialog样式。
//            SettingService settingService = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            你的dialog点击了确定调用：
//            settingService.execute();
//            你的dialog点击了取消调用：
//            settingService.cancel();
                }
            }
        };

        AndPermission.with(context)
                .requestCode(code)
                .permission(resultarr)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(contexts, rationale).
                                show();
//                                    rationale.resume();
                    }
                })
                .start();
        return wxlPermission;
     }


}
