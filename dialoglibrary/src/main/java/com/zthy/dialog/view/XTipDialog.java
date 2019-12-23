package com.zthy.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.zthy.dialog.R;
import com.zthy.dialog.base.TLoadView;
import com.zthy.dialog.view.Contanst.IconType;

public class XTipDialog extends Dialog {
    public XTipDialog(@NonNull Context context) {
        this(context, R.style.TipDialog);
    }

    public XTipDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    public static class Builder {

        private Context mContext;
        private boolean mCancelable = true;
        private IconType mCurrentIconType = IconType.ICON_TYPE_NOTHING;
        private String mTipWord;
        private int mTextSize=14;

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 设置 icon 显示的内容
         *
         * @see IconType
         */
        public Builder setIconType(IconType iconType) {
            this.mCurrentIconType = iconType;
            return this;
        }

        /**
         * 设置能不能取消
         */
        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        /**
         * 设置显示的文案
         */
        public Builder setTipWord(String tipWord) {
            mTipWord = tipWord;
            return this;
        }

        /**
         * 设置显示的文案
         */
        public Builder setTipWord(String tipWord,int textSize) {
            mTipWord = tipWord;
            mTextSize=textSize;
            return this;
        }

        public XTipDialog create() {
            return createDialog();
        }

        private XTipDialog createDialog() {
            XTipDialog dialog = new XTipDialog(mContext);
            dialog.setCancelable(mCancelable);
            dialog.setContentView(R.layout.x_dialog_tip_layout);

            LinearLayout mContentDialog = dialog.findViewById(R.id.contentDialog);
            if (mCurrentIconType == IconType.ICON_TYPE_LOADING) {
                TLoadView loadView = new TLoadView(mContext);
                loadView.setSize(dp2px(mContext, 32));
                loadView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                loadView.setColor(Color.WHITE);
                mContentDialog.addView(loadView);
            }

            if (mCurrentIconType == IconType.ICON_TYPE_SUCCESS
                    || mCurrentIconType == IconType.ICON_TYPE_FAIL
                    || mCurrentIconType == IconType.ICON_TYPE_INFO) {

                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                if (mCurrentIconType == IconType.ICON_TYPE_SUCCESS) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.x_ui_icon_notify_done));
                } else if (mCurrentIconType == IconType.ICON_TYPE_FAIL) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.x_ui_icon_notify_error));
                } else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.x_ui_icon_notify_info));
                }

                mContentDialog.addView(imageView);
            }

            if (mTipWord != null && mTipWord.length() > 0) {

                TextView tipTextView = new TextView(mContext);
                LinearLayout.LayoutParams tipViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                if (mCurrentIconType != IconType.ICON_TYPE_NOTHING) {
                    tipViewLP.topMargin = dp2px(mContext, 12);
                }

                tipTextView.setLayoutParams(tipViewLP);

                tipTextView.setEllipsize(TextUtils.TruncateAt.END);
                tipTextView.setGravity(Gravity.CENTER);
                tipTextView.setMaxLines(2);
                tipTextView.setTextColor(ContextCompat.getColor(mContext, R.color.xui_config_color_white));
                tipTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
                tipTextView.setText(mTipWord);

                mContentDialog.addView(tipTextView);
            }
            return dialog;
        }


    }

    /**
     * 传入自定义的布局并使用这个布局生成 TipDialog
     */
    public static class CustomBuilder {
        private Context mContext;
        private int mContentLayoutId;

        public CustomBuilder(Context context) {
            mContext = context;
        }

        public CustomBuilder setContent(@LayoutRes int layoutId) {
            mContentLayoutId = layoutId;
            return this;
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 {@link Dialog#show()} 方法
         *
         * @return 创建的 Dialog
         */
        public XTipDialog create() {
            XTipDialog dialog = new XTipDialog(mContext);
            dialog.setContentView(R.layout.x_dialog_tip_layout);
            ViewGroup contentWrap = (ViewGroup) dialog.findViewById(R.id.contentDialog);
            LayoutInflater.from(mContext).inflate(mContentLayoutId, contentWrap, true);
            return dialog;
        }
    }


    /**
     * 单位转换: dp -> px
     *
     * @param dp
     * @return
     */
    private static int dp2px(Context context, int dp) {
        return (int) (getDensity(context) * dp + 0.5);
    }

    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
