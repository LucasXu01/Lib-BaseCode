// (c)2016 Flipboard Inc, All Rights Reserved.

package com.lucas.lib_basecode.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.disposables.Disposable;

public abstract class XBaseFragment extends Fragment {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    /**
     * 上下文context
     **/
    public Activity mContext;
    /**
     * RXjava释放
     **/
    protected Disposable disposable;
    /**
     * fragment的全局变量view
     */
    public View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = (Activity) getActivity();
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(bindContentView(), container, false);

            afterview();
        }
        return view;
    }


    /**
     * 绑定view
     */
    public abstract int bindContentView();

    public abstract void afterview();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }


    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    @Override
    public void onResume() {
        super.onResume();


    }
}
