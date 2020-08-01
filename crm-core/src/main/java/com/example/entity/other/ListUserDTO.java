package com.example.entity.other;

import com.example.dto.UserDTO;

import java.util.List;

public class ListUserDTO {
    private List<UserDTO> items;
    public List<UserDTO> getItems() {
        return items;
    }

    public void setItems(List<UserDTO> items) {
        this.items = items;
    }
}
