package com.zthy.dialog.base;

import android.view.View;

import java.util.List;

/**
 * 通过适配器去管理设置底部bottom
 */
public abstract class AbsBottomAdapter {

    public abstract int size();//个数

    public abstract View getView(int postion);//获得底部按钮

    public abstract List<? extends Object> getDataList();

    public abstract Object getData(int postion);


}
