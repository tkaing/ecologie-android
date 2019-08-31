package com.example.recycle1.data.mapper;

import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<User> map (List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();
        for(UserDTO userDTO : userDTOList) {
            userList.add(map(userDTO));
        }
        return userList;
    }

    public static User map(UserDTO userDTO) {
        User user = new User();

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setBirthdate(userDTO.getBirthdate());
        user.setLocation(userDTO.getLocation());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }
}
