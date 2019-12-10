package com.zthy.dialog.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zthy.dialog.R;

import java.util.List;

public class DefaultDialogBottomAdapter extends AbsBottomAdapter {

    private Context mContext;
    private List<DefaultDialogBottomBean> mBottomTextList;


    public DefaultDialogBottomAdapter(Context context, List<DefaultDialogBottomBean> list) {

        mContext = context;
        mBottomTextList = list;
    }

    @Override
    int size() {
        return mBottomTextList.size();
    }

    @Override
    View getView(int postion) {

        // XDialogAction btAction = new XDialogAction.Builder(mContext).setText(mBottomTextList.get(postion).getText()).build();


        TextView btTextView = new TextView(mContext);
        btTextView.setGravity(Gravity.CENTER);

        btTextView.setText(mBottomTextList.get(postion).getText());
        btTextView.setTextColor(mContext.getResources().getColorStateList(mBottomTextList.get(postion).getColor()));

        btTextView.setBackgroundResource(R.drawable.x_ui_s_list_item_bg_with_border_none);
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
