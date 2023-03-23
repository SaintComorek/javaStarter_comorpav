package com.example.service;

import com.example.dto.BaseUserDto;
import com.example.dto.TagDto;
import com.example.dto.UserDto;
import com.example.model.BaseUserModel;
import com.example.model.Tag;
import com.example.model.User;
import com.example.repository.TagRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;

    @Autowired
    ModelMapper modelMapper;

    private  TagDto tagDto = new TagDto();
    private  Tag tag = new Tag();
    private User user = new User();
    private BaseUserModel baseUserModel;


    public List<Tag> findTagById(String tagName)
    {
        return tagRepo.findAll()
                .stream()
                .filter(w -> tag.getTagName() == tagName)
                .collect(Collectors.toList());

    }

    public List<Tag> findTagByUser(BaseUserDto baseUserDto)
    {

        return tagRepo.findAll()
                .stream()
                .filter(w -> tag.getBaseUserModel().getName().equals(baseUserDto.getName()))
                .collect(Collectors.toList());

    }


    public List<Tag> findTagByUserName(BaseUserDto baseUserDto)
    {

        return tagRepo.findAll()
                .stream()
                .filter(w -> tag.getBaseUserModel().equals(baseUserDto))
                //.filter(s -> tag.getTag() == "")
                .collect(Collectors.toList());

    }



/*
    public List<TagDto> getAllTags() {
        return this.tagRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    public List<Tag> putMethod(TagDto tagDto , long id) {
        Optional<Tag> optionalTag = tagRepo.findById(id);
        if (optionalTag.isPresent()) {
            deleteMethod(id);
            tag = convertDtoToEntity(tagDto);
            tagRepo.save(tag);
        }
        return tagRepo.findAll();
    }


    public List<Tag> deleteMethod(long id) {
        Optional<Tag> optionalTag = tagRepo.findById(id);
        if (optionalTag.isPresent()) {
            tag = optionalTag.get();
            tagRepo.delete(tag);
        }
        return tagRepo.findAll();
    }


 */
    public List<Tag> addTag(TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        tagRepo.save(tag);
        return tagRepo.findAll();
    }


    private TagDto convertEntityToDto(Tag tag) {
        tagDto = modelMapper.map(tag, TagDto.class);
        return tagDto;
    }

    private Tag convertDtoToEntity(TagDto tagDto) {
        tag = modelMapper.map(tagDto, Tag.class);
        return tag;
    }
    private BaseUserModel convertDtoToEntity(BaseUserDto baseUserDto) {
        baseUserModel = modelMapper.map(baseUserDto, BaseUserModel.class);
        return baseUserModel;
    }




    /*
    public List<UserDto> getListOfUsersDto() {
        if (!dtoCollection.getUserDtoList().isEmpty()) {
            return dtoCollection.getUserDtoList();
        }
        return Collections.emptyList();
    }

    public List<User> getListOfUsers() {
        if (!modelCollection.getUserList().isEmpty()) {
            return modelCollection.getUserList();
        }
        return Collections.emptyList();
    }


     */





}
