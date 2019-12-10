package com.zthy.dialog.base;

public class DialogDefautItemBean extends DialogBaseItemBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void setText(String text) {
        super.setText(name);//只要把想要显示的内容设置到text里面就可以了
    }

    public DialogDefautItemBean(String text, int id, String name) {
        super(text, id);
        this.name = name;
    }
}
