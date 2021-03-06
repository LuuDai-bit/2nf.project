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
