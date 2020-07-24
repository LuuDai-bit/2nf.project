package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name="role")
public class RoleEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private String code;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

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
}
