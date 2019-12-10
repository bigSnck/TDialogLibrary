package com.zthy.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zthy.dialog.base.AbsDialog;
import com.zthy.dialog.scrollview.XUIWrapContentScrollView;
import com.zthy.dialog.utils.DisplayUtils;

public class XMessageDialog extends AbsDialog {

    private LinearLayout mContainer;

    private TextView mTvContntText;//内容控件

    private TextView mTvCancle;//取消
    private TextView mTvOk;//确定

    private TextView mTvTitleText;

    private Builder mBuilder;
    private int mContentAreaMaxHeight = -1;
    private Drawable mCheckMarkDrawable;

    public XMessageDialog(Builder builder) {

        super(builder);

        mBuilder = builder;

        mCheckMarkDrawable = builder.mContext.getResources().getDrawable(R.drawable.ui_s_checkbox);

    }

    @Override
    public ViewGroup setDiaglogContanierParam() {
        mContainer = findViewById(R.id.x_dialog_container);
        return mContainer;
    }

    @Override
    public void setDiaglogTitleParam() {


        mTvTitleText = findViewById(R.id.x_dialog_message_title_tv);


        if (mBuilder.mTitleText.length() == 0) {

            mTvTitleText.setVisibility(View.GONE);

        } else {

            mTvTitleText.setVisibility(View.VISIBLE);

            mTvTitleText.setText(mBuilder.mTitleText);

        }

    }

    @Override
    public void setDiaglogContentParam() {


        XUIWrapContentScrollView mScrollContainer = findById(R.id.x_dialog_message_content_scrollview);


        mScrollContainer.setMaxHeight(mBuilder.mContentAreaMaxHeight);
        mScrollContainer.setVerticalScrollBarEnabled(false);


        mTvContntText = findViewById(R.id.x_dialog_message_content_tv);
        mTvContntText.setText(mBuilder.mMessage);


        /**
         * 是否显示checkbox
         */
        if (mBuilder.mIsCheckShow) {
            mCheckMarkDrawable.setBounds(0, 0, mCheckMarkDrawable.getIntrinsicWidth(), mCheckMarkDrawable.getIntrinsicHeight());

            mTvContntText.setCompoundDrawables(mCheckMarkDrawable, null, null, null);


            mTvContntText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBuilder.setChecked(!mBuilder.mIsChecked);
                    if (mTvContntText != null) {
                        mTvContntText.setSelected(mBuilder.mIsChecked);
                    }

                }
            });
            mTvContntText.setSelected(mBuilder.mIsChecked);
        }

    }


    public boolean getCheckState() {

        return mBuilder.mIsChecked;
    }


    public TextView getDiaglogContentTextView() {

        if (null == mTvContntText) {
            return null;
        }
        return mTvContntText;
    }



    public static class Builder extends AbsDialog.Builder<Builder> {

        private String mMessage = "确定要发送吗?";


        private String mTitleText = "标题";

        private int mContentAreaMaxHeight = -1;//设置内容最大高度

        private boolean mIsChecked;

        private boolean mIsCheckShow = false;//checkbox是否显示.默认不显示

        private int mMessageColor = R.color.xui_config_color_gray_4;
        private int mTitleColor = R.color.xui_config_color_black;


        public Builder(Context mContext) {
            super(mContext, R.layout.x_dialog_message_content_layout);

            mContentAreaMaxHeight = getContentAreaMaxHeight();
        }

        @Override
        public XMessageDialog build() {
            return new XMessageDialog(this);
        }


        /**
         * -------------------------------中间内容设置开始-------------------------
         **/

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }


        public Builder setMessage(String message, int messageColor) {
            this.mMessageColor = messageColor;
            return this;
        }

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


        /**
         * 设置 CheckBox 的勾选状态
         */
        public Builder setChecked(boolean checked) {
            if (mIsChecked != checked) {
                mIsChecked = checked;
            }

            return this;
        }

        /**
         * 设置 CheckBox是否显示
         */
        public Builder setShowCheck(boolean isshow) {
            if (mIsCheckShow != isshow) {
                mIsCheckShow = isshow;
            }

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
