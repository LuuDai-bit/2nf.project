package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class RoleEntity extends BaseEntity{
    @Column
    private String name;
    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
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
