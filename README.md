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
	implementation 'com.github.bigSnck:TDialogLibrary:1.0.0'
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
#### 效果图：<br>

