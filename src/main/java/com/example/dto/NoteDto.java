package com.example.dto;

import com.example.model.BaseUserModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto {
    @NotBlank(message = "Note id")
    private long id;

    @NotBlank(message = "Note name")
    private String name;

    @NotBlank(message = "tag name")
    private List<TagDto> tags ;

    @NotBlank(message = "value")
    private String value;

    @NotBlank(message = "User data")
    private BaseUserDto baseUserDto;


    public void NoteDto(String name , TagDto tagDto  , BaseUserModel baseUserModel){
        this.id = id;
        this.name = name;
        addToTagsList(tagDto);
        this.baseUserDto = baseUserDto;
    }

    public NoteDto(int i, String s, BaseUserDto baseUserDto) {
        this.id = id;
        this.value = s;
        this.tags = Collections.emptyList();
        this.value = value;
    }

    public void addToTagsList(TagDto tagDto) {
        tags.add(tagDto);
    }
}
