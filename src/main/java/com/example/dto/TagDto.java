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
public class TagDto {
    @NotBlank(message = "Group id ")
    private long id;

    @NotBlank(message = "Tag name ")
    private String tagName;


    private BaseUserDto baseUserDto;


}
