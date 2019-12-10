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
import android.widget.TextView;

import com.zthy.dialog.R;
import com.zthy.dialog.utils.DisplayUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class AbsDialog extends Dialog {

    private Builder mBuilder;
    private Context mContext;
    private ViewGroup mContainer;
    public List<View> mBottomView;


    public enum BottomStyle {
        STYLE_1,//带线
        STYLE_2//不带线
    }


    public AbsDialog(Builder builder) {
        this(R.style.UiDialog, builder);
    }

    public AbsDialog(int themeResId, Builder builder) {
        super(builder.mContext, themeResId);
        mContext = builder.mContext;
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
     * 获得所有底部bottomView
     *
     * @return
     */
    public View getBottomView(int postion) {

        if (postion < mBottomView.size()) {
            return mBuilder.mAbsBottomAdapter.getView(postion);

        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    public AbsBottomAdapter getAdapter(){
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
        wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);


        window.setGravity(Gravity.CENTER);

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
    private LinearLayout setDiaglogBottomParam() {

        if (mBuilder.mAbsBottomAdapter == null) {

            if (mBuilder.mDefaultList.size() == 0) {
                mBuilder.mDefaultList.add(new DefaultDialogBottomBean(R.color.app_color_blue, "确定"));
                mBuilder.mDefaultList.add(new DefaultDialogBottomBean(R.color.app_color_blue, "取消"));
            }

            mBuilder.mAbsBottomAdapter = new DefaultDialogBottomAdapter(mBuilder.mContext, mBuilder.mDefaultList);
        }


        for (int i = 0; i < mBuilder.mAbsBottomAdapter.size(); i++) {
            View view = mBuilder.mAbsBottomAdapter.getView(i);
            mBottomView.add(view);
        }


        LinearLayout xuiLinearLayout = new LinearLayout(mBuilder.mContext);
        xuiLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        xuiLinearLayout.setBackgroundResource(R.drawable.ui_s_linearlayout);

        int viewCount = mBottomView.size();
        if (viewCount > 4) {
            throw new IllegalArgumentException("bottom数量过多，影响显示效果,建议减少");
        } else {

            if (mBuilder.mBottomStyle == BottomStyle.STYLE_1) {
                createHorizontalLine();//横线
                createStyleButtom(xuiLinearLayout, viewCount);
            } else {

                xuiLinearLayout.setGravity(Gravity.RIGHT);
                createStyleButtom(xuiLinearLayout, viewCount);
            }

        }

        return xuiLinearLayout;
    }


    private void createStyleButtom(LinearLayout xuiLinearLayout, int viewCount) {


        for (int i = 0; i < viewCount; i++) {
            final View mView = (View) mBottomView.get(i);


            if (mBuilder.mBottomStyle == BottomStyle.STYLE_1) {
                LinearLayout.LayoutParams okParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                okParams.weight = 1;

                mView.setLayoutParams(okParams);
                mView.setPadding(0, DisplayUtils.dp2px(mBuilder.mContext, 12), 0, DisplayUtils.dp2px(mBuilder.mContext, 12));


                xuiLinearLayout.addView(mView);//底部按钮


                if (viewCount != 1 && i + 1 != viewCount) {

                    View verticalLine = createVerticalLine();
                    xuiLinearLayout.addView(verticalLine);//绘制竖线并且添加到View里面
                }

            } else {
                LinearLayout.LayoutParams okParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mView.setPadding(DisplayUtils.dp2px(mBuilder.mContext, 12), DisplayUtils.dp2px(mBuilder.mContext, 12), DisplayUtils.dp2px(mBuilder.mContext, 24), DisplayUtils.dp2px(mBuilder.mContext, 12));
                mView.setLayoutParams(okParams);
                xuiLinearLayout.addView(mView);

            }

            final int postion = i;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mBuilder.mBottomSelcetCallback) {
                        mBuilder.mBottomSelcetCallback.onSelectBottomItem(postion, mView);//设置点击事件

                    }
                }
            });

        }

        mContainer.addView(xuiLinearLayout);


    }


    private View createVerticalLine() {

        View mVerticalLineView = new View(mBuilder.mContext);


        mVerticalLineView.setBackgroundColor(mBuilder.mContext.getResources().getColor(R.color.xui_config_color_gray_4));
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(2, LinearLayout.LayoutParams.MATCH_PARENT);

        mVerticalLineView.setLayoutParams(lineParams);


        return mVerticalLineView;
    }


    private View createHorizontalLine() {

        View mHorizontalLineView = new View(mBuilder.mContext);

        mHorizontalLineView.setBackgroundColor(mBuilder.mContext.getResources().getColor(R.color.xui_config_color_gray_4));
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);

        mHorizontalLineView.setLayoutParams(lineParams);

        mContainer.addView(mHorizontalLineView);


        return mHorizontalLineView;
    }


    public static abstract class Builder<B extends Builder> {

        public Context mContext;
        private int mLayoutId;
        private int mWidth = 304;//dialog宽度
        private int mHigh;//dialog的高度
        private boolean mIsCancelTouchOutside;//是否能够取消


        public BottomStyle mBottomStyle = BottomStyle.STYLE_2;//不带线

        private IDialogBottomSelcetCallback mBottomSelcetCallback;

        private AbsBottomAdapter mAbsBottomAdapter;
        public List<DefaultDialogBottomBean> mDefaultList;

        public Builder(Context mContext, int layoutId) {
            this.mContext = mContext;
            this.mLayoutId = layoutId;
            mWidth = DisplayUtils.dp2px(mContext, mWidth);
            mDefaultList = new ArrayList<>();

        }


        public B setDialogWidthAndHigh(int width, int high) {

            this.mWidth = width;
            this.mHigh = high;
            return (B) this;
        }


        public B setCancelTouchOutside(boolean is) {

            this.mIsCancelTouchOutside = is;
            return (B) this;
        }


        /**
         * -------------------------------底部内容设置开始-------------------------
         **/
        public B setbottomAdapter(AbsBottomAdapter absBottomAdapter) {

            mAbsBottomAdapter = absBottomAdapter;

            return (B) this;
        }

        /**
         * 设置底部样式
         *
         * @param style
         * @return
         */
        public B setBottomStyle(BottomStyle style) {

            this.mBottomStyle = style;
            return (B) this;
        }


        /**
         * 设置底部点击事件
         *
         * @param callback
         * @return
         */
        public B setDialogBottomSelcetCallback(IDialogBottomSelcetCallback callback) {

            this.mBottomSelcetCallback = callback;
            return (B) this;
        }

        public B setBottomParameter(String btText, int btColor) {
            DefaultDialogBottomBean bottomBean = new DefaultDialogBottomBean(btColor, btText);
            mDefaultList.add(bottomBean);
            return (B) this;
        }

        /**
         * -------------------------------底部内容设置结束-------------------------
         **/


        public abstract AbsDialog build();


    }
}
