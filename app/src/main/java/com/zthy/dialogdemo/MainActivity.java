package com.zthy.dialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zthy.dialog.view.XEditextDialog;
import com.zthy.dialog.view.XListDialog;
import com.zthy.dialog.view.XMessageDialog;
import com.zthy.dialog.base.AbsDialog;
import com.zthy.dialog.base.CheckMode;
import com.zthy.dialog.view.adapter.DefautItAbsDialogContentContentItemBean;
import com.zthy.dialog.view.adapter.DialogListAdapter;
import com.zthy.dialog.base.IDialogBottomSelcetCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private XMessageDialog mMessageDialog;
    private XListDialog mListDialog;
    private XEditextDialog mEditextDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;


        findViewById(R.id.dialog_bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog1();
            }
        });


        findViewById(R.id.dialog_bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog2();
            }
        });
        findViewById(R.id.dialog_bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog3();
            }
        });
        findViewById(R.id.dialog_bt_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog4();
            }
        });
        findViewById(R.id.dialog_bt_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog5();
            }
        });
        findViewById(R.id.dialog_bt_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog6();
            }
        });
        findViewById(R.id.dialog_bt_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDialog7();
            }
        });
    }


    private void creatDialog1() {
        mMessageDialog = new XMessageDialog.Builder(mContext)
                .setTitleText("标题")//设置标题
                .setMessage("确定要发送吗?")///设置内容
                .setShowCheck(false)//是否显示checkbox
                .setBottomParameter("确定", R.color.app_color_blue)
                .setBottomStyle(AbsDialog.BottomStyle.STYLE_2)
                .setDialogBottomSelcetCallback(new IDialogBottomSelcetCallback() {
                    @Override
                    public void onSelectBottomItem(int position, View view) {


                    }
                }).build();


        mMessageDialog.show();

    }

    private void creatDialog2() {
        mMessageDialog = new XMessageDialog.Builder(mContext)
                .setMessage("很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长")
                .setTitleText("")//设置为空的时候不显示标题
                .build();

        mMessageDialog.show();
    }


    private void creatDialog3() {

        mMessageDialog = new XMessageDialog.Builder(mContext).setTitleText("退出后是否删除账号信息").setMessage("删除账号信息").setShowCheck(true).build();

        mMessageDialog.show();
    }


    private void creatDialog4() {

        final List<DefautItAbsDialogContentContentItemBean> mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(new DefautItAbsDialogContentContentItemBean("aa" + i, i, "aa" + i));
        }


        final DialogListAdapter mAdaper = new DialogListAdapter(mContext, mList, R.layout.x_dialog_item_content_layout);


        mListDialog = new XListDialog.Builder(mContext)
                .setTitleText("")
                .setCheckMode(CheckMode.SINGLE)//
                .setAdapter(mList, mAdaper)
                .build();

        mListDialog.show();
    }


    private void creatDialog5() {

        final List<DefautItAbsDialogContentContentItemBean> mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(new DefautItAbsDialogContentContentItemBean("aa" + i, i, "aa" + i));
        }


        final DialogListAdapter mAdaper = new DialogListAdapter(mContext, mList, R.layout.x_dialog_item_content_layout);


        mListDialog = new XListDialog.Builder(mContext)
                .setTitleText("")
                .setCheckMode(CheckMode.MULTI)//多选
                .setAdapter(mList, mAdaper)
                .build();

        mListDialog.show();
    }

    private void creatDialog6() {
        final List<DefautItAbsDialogContentContentItemBean> mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add(new DefautItAbsDialogContentContentItemBean("aa" + i, i, "aa" + i));
        }


        final DialogListAdapter mAdaper = new DialogListAdapter(mContext, mList, R.layout.x_dialog_item_content_layout);


        mListDialog = new XListDialog.Builder(mContext)
                .setTitleText("")
                .setCheckMode(CheckMode.MULTI)//多选
                .setAdapter(mList, mAdaper)
                .build();

        mListDialog.show();
    }

    private void creatDialog7() {


        mEditextDialog = new XEditextDialog.Builder(mContext)
                .setTitleText("标题")
                .build();

        mEditextDialog.show();
    }

}
