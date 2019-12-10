package com.zthy.dialog.base;

public abstract class DialogBaseItemBean {

    public DialogBaseItemBean(String text, int id) {
        this.text = text;
        this.id = id;
    }

    String text;//显示的内容
    int id;//列表的id

    boolean isCheecked;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheecked() {
        return isCheecked;
    }

    public void setCheecked(boolean cheecked) {
        isCheecked = cheecked;
    }
}
