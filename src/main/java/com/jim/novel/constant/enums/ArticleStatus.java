package com.jim.novel.constant.enums;

/**文章状态枚举
 * Created by run on 17/3/20.
 */
public enum ArticleStatus  {

    ORIGIN(0,"待审核"),
    PASSED(1,"通过审核"),
    NOPASS(-1,"未通过");

    ArticleStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
