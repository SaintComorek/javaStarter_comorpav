package com.example.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseUserDto {
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    /*
    @NotBlank(message = "age")
    private int age;
    @NotBlank(message = "status")
    private String status;

     */

    public void setUpBaseData(UserDto userDto) {
        this.id = (userDto.getId());
        this.name = (userDto.getName());
        this.lastName = (userDto.getLastName());
       // this.age = userDto.getAge();
      //  this.status = userDto.getStatus();
    }

}
