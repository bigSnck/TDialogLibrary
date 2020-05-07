package com.zthy.dialog.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yt.baseadapterlibrary.TBaseAdapter;
import com.yt.baseadapterlibrary.view.OnItemClickCallback;
import com.zthy.dialog.R;
import com.zthy.dialog.base.AbsDialog;
import com.zthy.dialog.base.AbsDialogBaseContentItemBean;
import com.zthy.dialog.base.CheckMode;
import com.zthy.dialog.base.XLinearLayout;
import com.zthy.dialog.base.scrollview.XUIWrapContentScrollView;
import com.zthy.dialog.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class XBottomListDialog extends AbsDialog {

    private XLinearLayout mContainer;


    private TextView mTvTitleText;

    private Builder mBuilder;
    private int mContentAreaMaxHeight = -1;

    private RecyclerView mRecyclerView;


    public XBottomListDialog(Builder builder) {

        super(builder);

        mBuilder = builder;

    }

    /**
     * 设置背景
     */
    @Override
    public ViewGroup setDiaglogContanierParam() {
        mContainer = findViewById(R.id.x_dialog_container);

        mContainer.setRadius(20);//设置背景圆角

        return mContainer;
    }

    /**
     * 设置标题
     */
    @Override
    public void setDiaglogTitleParam() {

        if (mBuilder.mTitleLayoutView == null) {
            mBuilder.mTitleLayoutView = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.x_dialog_item_title_layout, null);
        }

        if (mBuilder.mTitleLayoutView == null) {
            mTvTitleText = mBuilder.mTitleLayoutView.findViewById(R.id.x_dialog_list_title_tv);

            if (mBuilder.mTitleText.length() == 0) {
                mTvTitleText.setVisibility(View.GONE);

            } else {
                mTvTitleText.setVisibility(View.VISIBLE);
                mTvTitleText.setText(mBuilder.mTitleText);

            }
        }


        mContainer.addView(mBuilder.mTitleLayoutView,0);
    }

    /**
     * 设置内容
     */
    @Override
    public void setDiaglogContentParam() {


        XUIWrapContentScrollView mScrollContainer = findById(R.id.x_dialog_list_content_scrollview);


        mRecyclerView = findById(R.id.x_dialog_list_content_recyclerview);


        mScrollContainer.setMaxHeight(mBuilder.mContentAreaMaxHeight);
        mScrollContainer.setVerticalScrollBarEnabled(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mBuilder.mContext));

        if (null != mBuilder.mAdaper) {
            mRecyclerView.setAdapter(mBuilder.mAdaper);

            if (mBuilder.mAdaper instanceof TBaseAdapter) {
                TBaseAdapter mAdapter = (TBaseAdapter) mBuilder.mAdaper;

                mAdapter.setOnItemClickCallback(new OnItemClickCallback() {
                    @Override
                    public void onClick(Object o, int position) {
                        upCheckData(position, mBuilder.mDataList);
                    }
                });
            }

        }


    }


    /**
     * 获取已经选中的条目
     *
     * @param <D>
     * @return
     */
    public <D extends AbsDialogBaseContentItemBean> List<D> getCheckedData() {
        List<D> mResultList = new ArrayList();
        mResultList.clear();

        if (!mBuilder.mDataList.isEmpty()) {

            for (int i = 0; i < mBuilder.mDataList.size(); i++) {
                D result = (D) mBuilder.mDataList.get(i);
                if (result instanceof AbsDialogBaseContentItemBean) {
                    if (result.isCheecked()) {
                        mResultList.add(result);
                    }
                }
            }
        }
        return mResultList;

    }


    /**
     * 更新选中的数据
     *
     * @param position
     * @param mDataList
     */
    private void upCheckData(int position, List<AbsDialogBaseContentItemBean> mDataList) {

        if (mBuilder.mCheckMode == CheckMode.SINGLE) {

            for (AbsDialogBaseContentItemBean itemBean : mDataList) {
                if (itemBean.isCheecked()) {
                    itemBean.setCheecked(false);
                    mBuilder.mAdaper.notifyItemChanged(position);

                }

            }

            mDataList.get(position).setCheecked(!mDataList.get(position).isCheecked());//取选中的反值即可，并且保证只有一个选中

            mBuilder.mAdaper.notifyDataSetChanged();

        }

        if (mBuilder.mCheckMode == CheckMode.MULTI) {
            mDataList.get(position).setCheecked(!mDataList.get(position).isCheecked());//取选中的反值即可
            mBuilder.mAdaper.notifyDataSetChanged();

        }


    }


    public static class Builder extends AbsDialog.Builder<Builder> {

        private String mTitleText = "标题";

        private int mContentAreaMaxHeight = -1;//设置内容最大高度


        private CheckMode mCheckMode = CheckMode.SINGLE;//默认单选模式


        private int mListColor = R.color.xui_config_color_gray_4;
        private int mTitleColor = R.color.xui_config_color_black;


        private RecyclerView.Adapter mAdaper;
        private List mDataList;


        public Builder(Context mContext) {
            super(mContext, R.layout.x_dialog_list_content_layout);

            mContentAreaMaxHeight = getContentAreaMaxHeight();
        }

        @Override
        public XBottomListDialog build() {
            return new XBottomListDialog(this);
        }


        /**
         * -------------------------------上部内容设置开始-------------------------
         **/
        public Builder setTitleText(String titleText, int titleColor) {
            this.mTitleText = titleText;
            this.mTitleColor = titleColor;

            return this;
        }


        public Builder setTitleText(String titleText) {
            this.mTitleText = titleText;

            return this;
        }


        /**
         * -------------------------------上部内容设置结束-------------------------
         **/


        /**
         * -------------------------------中间内容设置开始-------------------------
         **/

        /**
         * 设置单选模式还是多选模式
         *
         * @param checkMode
         * @return
         */
        public Builder setCheckMode(CheckMode checkMode) {
            this.mCheckMode = checkMode;

            return this;
        }

        /**
         * -------------------------------中间内容设置结束-------------------------
         **/


        public Builder setContentAreaMaxHeight(int contentAreaMaxHeight) {
            this.mContentAreaMaxHeight = contentAreaMaxHeight;

            return this;
        }


        public <D extends AbsDialogBaseContentItemBean> Builder setAdapter(List<D> data, RecyclerView.Adapter adaper) {

            this.mAdaper = adaper;
            this.mDataList = data;

            return this;

        }

        protected int getContentAreaMaxHeight() {
            if (mContentAreaMaxHeight == -1) {
                // 屏幕高度的0.85 - 预估的 title 和 action 高度
                return (int) (DisplayUtils.getScreenHeight(mContext) * 0.6) - DisplayUtils.dp2px(mContext, 100);
            }
            return mContentAreaMaxHeight;
        }

    }


}
