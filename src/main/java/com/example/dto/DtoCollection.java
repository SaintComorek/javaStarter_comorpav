package com.example.dto;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DtoCollection {

    private List<UserDto> userDtoList = new ArrayList<>();
    private List<NoteDto> noteDtoList = new ArrayList<>();
    private List<TagDto> tagDtoList = new ArrayList<>();
    private List<GroupDto> groupDtoList = new ArrayList<>();
    private List<BaseUserDto> baseUserDtoList = new ArrayList<>();

    public void addUserDto(UserDto userDto)
    {
        userDtoList.add(userDto);
    }
    public void addNoteDto(NoteDto note)
    {
        noteDtoList.add(note);
    }
    public void addTagDto(TagDto tag)
    {
        tagDtoList.add(tag);
    }
    public void addGroupDto(GroupDto groupDto)
    {
        groupDtoList.add(groupDto);
    }
    public void addBaseUserDto(BaseUserDto baseUserDto)
    {
        baseUserDtoList.add(baseUserDto);
    }

}
