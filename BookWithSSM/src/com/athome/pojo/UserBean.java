package com.athome.pojo;


import org.springframework.stereotype.Repository;

@Repository
public class UserBean {
    String name;
    String password;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserBean(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserBean() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
