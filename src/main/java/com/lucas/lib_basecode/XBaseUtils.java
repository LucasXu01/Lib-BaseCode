package com.lucas.lib_basecode;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * 保留所有版权，禁止分享
 * author : 许进进
 * time   : 2020/6/19 4:57 PM
 * des    : 常用的工具类代码
 */
public class XBaseUtils {

    /**
     * 检查是否安装qq
     * @author lucas
     * created at 2019/5/6 4:57 PM
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 跳转带qq联系人
     * @author lucas
     * created at 2019/5/6 4:57 PM
     */
    public static void jump2qq(Context context, String qq_number) {
        if (XBaseUtils.checkApkExist(context, "com.tencent.mobileqq")) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qq_number + "&version=1")));
        } else {
            Toast.makeText(context,"本机未安装QQ应用",Toast.LENGTH_SHORT).show();
        }
    }



}
