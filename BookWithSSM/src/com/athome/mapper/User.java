package com.athome.mapper;

import com.athome.pojo.UserBean;

import java.util.List;

public interface User {
    public List<UserBean> getAllUser();
    public UserBean getUserByNameAndPassword(String userName,String password);

    public UserBean getUserByName(String userName);

    public void insertUser(UserBean userBean);

}
