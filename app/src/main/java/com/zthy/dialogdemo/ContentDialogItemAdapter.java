package com.zthy.dialogdemo;

import android.content.Context;
import android.widget.ImageView;

import com.yt.baseadapterlibrary.TBaseAdapter;
import com.yt.baseadapterlibrary.view.ViewHolder;
import com.zthy.dialog.view.adapter.DefautItAbsDialogContentContentItemBean;

import java.util.List;

public class ContentDialogItemAdapter extends TBaseAdapter<DefautItAbsDialogContentContentItemBean> {
    public ContentDialogItemAdapter(Context context, List<DefautItAbsDialogContentContentItemBean> data) {
        super(context, data, R.layout.item_content_layout);
    }

    @Override
    public void convert(ViewHolder holder, DefautItAbsDialogContentContentItemBean defautItAbsDialogContentContentItemBean) {
        holder.setText(R.id.x_dialog_list_item_tv, defautItAbsDialogContentContentItemBean.getText());

        ImageView iv = (ImageView) holder.getView(R.id.x_dialog_list_item_iv);
        iv.setSelected(defautItAbsDialogContentContentItemBean.isCheecked);
    }
}
