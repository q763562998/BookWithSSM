package com.athome.service;

import com.athome.mapper.User;
import com.athome.pojo.BookBean;
import com.athome.pojo.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    User user;
    @Override
    public List<UserBean> getAllUser() {
        List<UserBean> list = user.getAllUser();
        return list;
    }

    @Override
    public UserBean getUserByNameAndPassword(String userName,String password) {
        UserBean bean = user.getUserByNameAndPassword(userName,password);
        return bean;
    }

    @Override
    public UserBean getUserByName(String userName) {
        UserBean user = this.user.getUserByName(userName);
        return user;
    }

    @Override
    public void insertUser(UserBean userBean) {
        user.insertUser(userBean);
    }




}
