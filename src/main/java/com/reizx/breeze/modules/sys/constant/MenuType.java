package com.reizx.breeze.modules.sys.constant;

public enum  MenuType {
    CATALOG(0),//目录
    MENU(1),//菜单
    BUTTON(2);//按钮

    private int value;
    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
