package com.zthy.dialog.view.adapter;

import android.content.Context;
import android.widget.ImageView;


import com.zthy.dialog.R;
import com.zthy.dialog.adapter.CommonRecyclerAdapter;
import com.zthy.dialog.adapter.ViewHolder;

import java.util.List;

public class DialogListAdapter extends CommonRecyclerAdapter<DefautItAbsDialogContentContentItemBean> {
    public DialogListAdapter(Context context, List<DefautItAbsDialogContentContentItemBean> data, int layoutId) {
        super(context, data, layoutId);

    }

    @Override
    public void convert(ViewHolder holder, DefautItAbsDialogContentContentItemBean item) {


        holder.setText(R.id.x_dialog_list_item_tv, item.getText());

        ImageView iv = holder.getView(R.id.x_dialog_list_item_iv);
        iv.setSelected(item.isCheecked);
    }
}
