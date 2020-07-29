package com.example.entity.other;

import com.example.dto.UserDTO;

import java.util.List;

public class ListUserDTO {
    public List<UserDTO> getItems() {
        return items;
    }

    public void setItems(List<UserDTO> items) {
        this.items = items;
    }

    private List<UserDTO> items;
}
