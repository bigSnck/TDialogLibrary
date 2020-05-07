package com.zthy.dialog.view.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zthy.dialog.R;
import com.zthy.dialog.base.AbsBottomAdapter;

import java.util.List;

public class DefaultDialogBottomAdapter extends AbsBottomAdapter {

    private Context mContext;
    private List<DefaultDialogBottomBean> mBottomTextList;


    public DefaultDialogBottomAdapter(Context context, List<DefaultDialogBottomBean> list) {

        mContext = context;
        mBottomTextList = list;
    }


    @Override
    public int size() {
        return mBottomTextList.size();
    }

    @Override
    public View getView(int postion) {

        TextView btTextView = new TextView(mContext);
        btTextView.setGravity(Gravity.CENTER);

        btTextView.setText(mBottomTextList.get(postion).getText());
        btTextView.setTextSize(16);
        btTextView.setTextColor(mContext.getResources().getColorStateList(mBottomTextList.get(postion).getColor()));

        btTextView.setBackgroundResource(R.drawable.x_ui_list_item_bg);
        btTextView.setClickable(true);

        return btTextView;
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
