package com.zthy.dialog.view.adapter;

import android.content.Context;
import android.widget.ImageView;


import com.yt.baseadapterlibrary.TBaseAdapter;
import com.yt.baseadapterlibrary.view.ViewHolder;
import com.zthy.dialog.R;


import java.util.List;

public class DialogListAdapter extends TBaseAdapter<DefautItAbsDialogContentContentItemBean> {
    public DialogListAdapter(Context context, List<DefautItAbsDialogContentContentItemBean> data, int layoutId) {
        super(context, data, layoutId);

    }

    @Override
    public void convert(ViewHolder holder, DefautItAbsDialogContentContentItemBean item) {


        holder.setText(R.id.x_dialog_list_item_tv, item.getText());

        ImageView iv = (ImageView) holder.getView(R.id.x_dialog_list_item_iv);
        iv.setSelected(item.isCheecked);
    }
}
