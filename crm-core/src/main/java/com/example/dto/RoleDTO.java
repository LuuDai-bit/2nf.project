package com.example.dto;

import com.example.entity.UserEntity;

import java.util.List;

public class RoleDTO extends BaseDTO{

    private String name;

    private String code;

    private List<UserEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public List<UserEntity> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserEntity> users) {
//        for(UserEntity item: users){
//
//        }
//    }
}
