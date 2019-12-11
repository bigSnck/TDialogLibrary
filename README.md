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

### 具体使用Demo
