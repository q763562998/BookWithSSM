package com.athome.service;

import com.athome.mapper.User;
import com.athome.pojo.BookBean;
import com.athome.pojo.UserBean;

import java.util.List;

public interface UserService {
    public List<UserBean> getAllUser();

    public UserBean getUserByNameAndPassword(String userName,String password);

    public UserBean getUserByName(String userName);

    public void insertUser(UserBean userBean);






}
