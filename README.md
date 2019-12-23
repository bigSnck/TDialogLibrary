### 添加到Android studio<br>
##### Step1: 在根build.gradle中添加仓库：<br>

```
allprojects {
	repositories {
		
		maven { url 'https://jitpack.io' }
	}
}
```
##### 注意:maven { url "https://jitpack.io" }一定要放到 allprojects 里面不然更新不下来
#### Step2: 在工程中添加依赖：<br>
```
dependencies {
	implementation 'com.github.bigSnck:TDialogLibrary:1.0.1'
}
```

### 具体使用Demo<br>
#### 通常普通用法<br>
##### 代码：<br>
```
mMessageDialog = new XMessageDialog.Builder(mContext)
                .setTitleText("标题")//设置标题
                .setMessage("确定要发送吗?")///设置内容
                .setShowCheck(false)//是否显示checkbox
                .setBottomParameter("确定", R.color.app_color_blue)
                .setBottomStyle(AbsDialog.BottomStyle.STYLE_1)
                .setDialogBottomSelcetCallback(new IDialogBottomSelcetCallback() {
                    @Override
                    public void onSelectBottomItem(int position, View view) {


                    }
                }).build();


mMessageDialog.show();
```
##### 效果图：<br>

<img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_a.png" width="300" height="500"/>   <img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_a1.png" width="300" height="500"/>  <img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_a2.png" width="300" height="500"/>

#### 多选和单选的dialog <br>
##### 代码 <br>
```
//退出登录
mMessageDialog = new XMessageDialog.Builder(mContext).setTitleText("退出后是否删除账号信息").setMessage("删除账号信息").setShowCheck(true).build();
   mMessageDialog.show();



final List<DefautItAbsDialogContentContentItemBean> mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(new DefautItAbsDialogContentContentItemBean("aa" + i, i, "aa" + i));
        }


final DialogListAdapter mAdaper = new DialogListAdapter(mContext, mList, R.layout.x_dialog_item_content_layout);

//单选
mListDialog = new XListDialog.Builder(mContext)
                .setTitleText("")
                .setCheckMode(CheckMode.SINGLE)//单选
                .setAdapter(mList, mAdaper)
                .build();

        mListDialog.show();

//多选
mListDialog = new XListDialog.Builder(mContext)
                .setTitleText("")
                .setCheckMode(CheckMode.MULTI)//多选
                .setAdapter(mList, mAdaper)
                .build();

        mListDialog.show();
```
##### 效果图<br>

<img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_b.png" width="300" height="500"/> 
<img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_b1.png" width="300" height="500"/>
 <img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_b2.png" width="300" height="500"/>
 
 
 #### 输入框dialog <br>
 ##### 代码 <br>
 ``` 
  mEditextDialog = new XEditextDialog.Builder(mContext)
                .setTitleText("标题")
                .build();
  mEditextDialog.show();
  mEditextDialog.getEtContent();//输入框里面的内容
 ```
 ##### 效果图<br>
 <img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/style_c.png" width="300" height="500"/> 
 
 
  #### 正在加载中,加载成功,加载失败,加载图标<br>
  ##### 代码 <br>
   ``` 
   mXTipDialog = new XTipDialog.Builder(mContext)
               .setTipWord("正在加载中")
	       .setIconType(IconType.ICON_TYPE_LOADING) //ICON_TYPE_SUCCESS,ICON_TYPE_FAIL,ICON_TYPE_INFO
	       .setCancelable(true)//是否能通过点击周围取消 true：能 false:不能
	       .create();
   mXTipDialog.show();
   ``` 
##### 效果图<br>
<img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/dialog_loading.jpg" width="300" height="500"/>     <img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/dialog_success.jpg" width="300" height="500"/>     <img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/dialog_fail.jpg" width="300" height="500"/> 
<img src="https://github.com/bigSnck/TDialogLibrary/blob/master/image/dialog_info.jpg" width="300" height="500"/> 
 
 
  #### 更多用法可以查看源码 谢谢！！ <br>
