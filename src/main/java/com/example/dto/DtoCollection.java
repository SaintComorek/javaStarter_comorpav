package com.example.dto;


import com.example.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DtoCollection {

    private List<UserDto> userDtoList = new ArrayList<>();

    public void addUserDto(UserDto userDto)
    {

        userDtoList.add(userDto);
    }

}
