package com.zthy.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zthy.dialog.R;
import com.zthy.dialog.utils.DisplayUtils;
import com.zthy.dialog.view.adapter.DefaultDialogBottomAdapter;
import com.zthy.dialog.view.adapter.DefaultDialogBottomBean;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseDialog extends Dialog {

    private Builder mBuilder;

    private ViewGroup mContainer;
    public List<View> mBottomView;



    public BaseDialog(Builder builder) {
        this(R.style.UiDialog, builder);
    }

    public BaseDialog(int themeResId, Builder builder) {
        super(builder.mContext, themeResId);
        mBuilder = builder;
        mBottomView = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDialog();
    }


    /**
     * 用户可以随便获取控件并进行操作
     *
     * @param id
     * @param <V>
     * @return
     */
    public <V extends View> V findById(int id) {

        return (V) findViewById(id);
    }


    /**
     * 获得所有底部bottomView
     *
     * @return
     */
    public List<View> getBottomViews() {
        return mBottomView;
    }


    /**
     * 获得底部bottomView,可以获取设置文字的大小，颜色，内容，是否可以点击等
     *
     * @return
     */
    public View getBottomView(int postion) {

        if (postion < mBottomView.size()) {
            return mBottomView.get(postion);

        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    public AbsBottomAdapter getAdapter() {
        return mBuilder.mAbsBottomAdapter;
    }

    private void initDialog() {

        setContentView(mBuilder.mLayoutId);
        setCanceledOnTouchOutside(mBuilder.mIsCancelTouchOutside);

        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams wmlp = window.getAttributes();
        wmlp.width = mBuilder.mWidth;
        wmlp.gravity = mBuilder.mGravity;
        window.setAttributes(wmlp);

        window.setGravity(mBuilder.mGravity);

        mContainer = setDiaglogContanierParam();
        setDiaglogTitleParam();
        setDiaglogContentParam();
        setDiaglogBottomParam();
    }


    /**
     * 设置背景
     */
    public abstract ViewGroup setDiaglogContanierParam();

    /**
     * 设置dialog上标题
     */
    public abstract void setDiaglogTitleParam();

    /**
     * 设置dialog中间内容
     */
    public abstract void setDiaglogContentParam();

    /**
     * 设置dialog底部
     */
    public abstract void setDiaglogBottomParam();


    public static abstract class Builder<B extends Builder> {

        public Context mContext;
        private int mLayoutId;
        private int mWidth = 304;//dialog宽度
        private int mGravity = Gravity.CENTER;
        public View mTitleLayoutView;//标题的布局文件
        public View mContentLayoutView;//内容的布局文件
        public View mBottomLayoutView;//底部的布局文件
        private int mHigh;//dialog的高度
        private boolean mIsCancelTouchOutside;//是否能够取消



        private IDialogBottomSelcetCallback mBottomSelcetCallback;

        private AbsBottomAdapter mAbsBottomAdapter;
        public List<DefaultDialogBottomBean> mDefaultList;

        public Builder(Context mContext, int layoutId) {
            this.mContext = mContext;
            this.mLayoutId = layoutId;
            mWidth = DisplayUtils.dp2px(mContext, mWidth);
            mDefaultList = new ArrayList<>();

        }

        public B setTitleLayoutView(View titleView) {
            this.mTitleLayoutView = titleView;
            return (B) this;
        }

        public B setContentLayoutView(View cotentView) {
            this.mContentLayoutView = cotentView;
            return (B) this;
        }

        public B setBottomLayoutView(View bottomView) {
            this.mBottomLayoutView = bottomView;
            return (B) this;
        }


        public B setDialogWidthAndHigh(int width, int high) {

            this.mWidth = width;
            this.mHigh = high;
            return (B) this;
        }


        public B setGravity(int gravity) {

            this.mGravity = gravity;
            return (B) this;
        }

        public B setCancelTouchOutside(boolean is) {

            this.mIsCancelTouchOutside = is;
            return (B) this;
        }


        /**
         * -------------------------------底部内容设置开始-------------------------
         **/



        /**
         * -------------------------------底部内容设置结束-------------------------
         **/


        public abstract BaseDialog build();


    }
}
