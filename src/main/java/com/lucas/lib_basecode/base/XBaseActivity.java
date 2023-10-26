// (c)2019 YanFriends Inc, All Rights Reserved.
package com.lucas.lib_basecode.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;

import io.reactivex.disposables.Disposable;


/**
 * @author 许进进
 */
public abstract class XBaseActivity extends AppCompatActivity {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    /**
     * 上下文context
     **/
    public XBaseActivity mContext;
    /**
     * RXjava释放
     **/
    protected Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        beforeView();
        setContentView(bindContentView());
        afterView();
    }


    public void beforeView() {

    }

    /**
     * 绑定view
     */
    public abstract View bindContentView();

    public void afterView() {
        /**    隐藏actionbar  沉浸式状态栏   */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //融合状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        BarUtils.setStatusBarColor(this, Color.WHITE, true);
        //加上下面这两行
        ViewGroup contentParent = findViewById(android.R.id.content);
        contentParent.getChildAt(0).setFitsSystemWindows(true);

        /**    更改状态栏为黑字体     */
        changeStatusBarTextColor(true);


        /**   每个页面如果有返回按键  给该返回按键添加事件     */
//        if(findViewById(R.id.img_back)!=null){
//            findViewById(R.id.img_back).setOnClickListener(v->{onBackPressed();});
//        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }


    /**
     * RXjava释放
     **/
    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    public void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }


}



