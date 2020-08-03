package com.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{
    private static final long serialVersionUID = 6334633814825073605L;
    @Column
    private String name;
    @Column
    private String email;
    @Column(nullable=false)
    private Long role_id;
    @Column
    private String phone;
    @Column
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
