package com.zthy.dialog.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;


import com.zthy.dialog.R;
import com.zthy.dialog.base.AbsDialog;
import com.zthy.dialog.base.XLinearLayout;
import com.zthy.dialog.base.scrollview.XUIWrapContentScrollView;
import com.zthy.dialog.utils.DisplayUtils;

/**
 * 带输入框的Dialog
 */

public class XEditextDialog extends AbsDialog {

    private XLinearLayout mContainer;


    private TextView mTvCancle;//取消
    private TextView mTvOk;//确定

    private TextView mTvTitleText;


    private AppCompatEditText mEditText;

    private Builder mBuilder;
    private int mContentAreaMaxHeight = -1;
    private Drawable mCheckMarkDrawable;

    public XEditextDialog(Builder builder) {

        super(builder);

        mBuilder = builder;

        mCheckMarkDrawable = builder.mContext.getResources().getDrawable(R.drawable.ui_s_checkbox);

    }

    @Override
    public ViewGroup setDiaglogContanierParam() {
        mContainer = findViewById(R.id.x_dialog_container);
        mContainer.setRadius(20);
        return mContainer;
    }

    @Override
    public void setDiaglogTitleParam() {


        mTvTitleText = findViewById(R.id.x_dialog_editext_title_tv);


        if (mBuilder.mTitleText.length() == 0) {

            mTvTitleText.setVisibility(View.GONE);

        } else {

            mTvTitleText.setVisibility(View.VISIBLE);

            mTvTitleText.setText(mBuilder.mTitleText);

        }

    }

    @Override
    public void setDiaglogContentParam() {


        XUIWrapContentScrollView mScrollContainer = findById(R.id.x_dialog_editext_content_scrollview);

        mEditText = findById(R.id.x_dialog_editext_content_et);
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_GO);

        mScrollContainer.setMaxHeight(mBuilder.mContentAreaMaxHeight);
        mScrollContainer.setVerticalScrollBarEnabled(false);


    }


    public String getEtContent() {
        return mEditText.getText().toString();
    }

    public TextView getDiaglogBottomCancleTextView() {
        if (null == mTvCancle) {
            return null;
        }
        return mTvCancle;
    }


    public TextView getDiaglogBottomOkTextView() {
        if (null == mTvOk) {
            return null;
        }
        return mTvOk;
    }

    public static class Builder extends AbsDialog.Builder<Builder> {


        private String mTitleText = "标题";

        private int mContentAreaMaxHeight = -1;//设置内容最大高度

        private int mTitleColor = R.color.xui_config_color_black;


        public Builder(Context mContext) {
            super(mContext, R.layout.x_dialog_editext_content_layout);
            mContentAreaMaxHeight = getContentAreaMaxHeight();
        }

        @Override
        public XEditextDialog build() {
            return new XEditextDialog(this);
        }


        /**
         * -------------------------------中间内容设置开始-------------------------
         **/

        /**-------------------------------中间内容设置结束-------------------------**/


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


        public Builder setContentAreaMaxHeight(int contentAreaMaxHeight) {
            this.mContentAreaMaxHeight = contentAreaMaxHeight;

            return this;
        }


        protected int getContentAreaMaxHeight() {
            if (mContentAreaMaxHeight == -1) {
                // 屏幕高度的0.85 - 预估的 title 和 action 高度
                return (int) (DisplayUtils.getScreenHeight(mContext) * 0.85) - DisplayUtils.dp2px(mContext, 100);
            }
            return mContentAreaMaxHeight;
        }


    }


}
