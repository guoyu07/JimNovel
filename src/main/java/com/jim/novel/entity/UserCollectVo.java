package com.jim.novel.entity;

import com.jim.novel.model.Article;
import com.jim.novel.model.UserCollect;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author run
 * @create 2017-05-13
 **/
public class UserCollectVo extends Article {
    private Integer collectId;
    private Date time;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
