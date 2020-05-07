package com.zthy.dialogdemo;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zthy.dialog.base.AbsBottomAdapter;
import com.zthy.dialog.base.XLinearLayout;
import com.zthy.dialog.view.adapter.DefaultDialogBottomBean;

import java.util.List;


public class BottomDialogAdapter extends AbsBottomAdapter {
    private Context mContext;
    private List<DefaultDialogBottomBean> mBottomTextList;
    private ViewGroup mViewGroup;


    public BottomDialogAdapter(Context context, List<DefaultDialogBottomBean> list,ViewGroup viewGroup) {

        mContext = context;
        mBottomTextList = list;
        mViewGroup=viewGroup;
    }


    @Override
    public int size() {
        return mBottomTextList.size();
    }

    @Override
    public View getView(int postion) {

        XLinearLayout view = (XLinearLayout) LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_structions_layout, mViewGroup,false);
        view.setRadius(5);
        TextView tv = view.findViewById(R.id.bottom_ok_tv);
        tv.setText(mBottomTextList.get(postion).getText());
        return view;
    }


    @Override
    public List<? extends Object> getDataList() {
        return mBottomTextList;
    }

    @Override
    public Object getData(int postion) {
        return mBottomTextList.get(postion);
    }

}
