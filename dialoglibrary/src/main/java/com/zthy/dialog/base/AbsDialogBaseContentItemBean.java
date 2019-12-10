package com.zthy.dialog.base;

public abstract class AbsDialogBaseContentItemBean {

    public AbsDialogBaseContentItemBean(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public String text;//显示的内容
    public int id;//列表的id

    public boolean isCheecked;

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
