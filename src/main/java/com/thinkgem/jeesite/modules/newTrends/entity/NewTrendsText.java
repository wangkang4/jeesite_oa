package com.thinkgem.jeesite.modules.newTrends.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class NewTrendsText extends DataEntity<NewTrendsText> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * 顺序
     */
    private int number;
    /**
     * 文本内容
     */
    private String text;

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }


}
