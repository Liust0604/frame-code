package com.mori.demo.domain;

import java.util.List;

/**
 * QueryVo是User的封装类
 */
public class QueryVo {
    private User user;

    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
