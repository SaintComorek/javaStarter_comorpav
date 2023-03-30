package com.example.service;

import com.example.dto.BaseUserDto;
import com.example.dto.TagDto;
import com.example.model.BaseUserModel;
import com.example.model.Tag;
import com.example.model.User;
import com.example.repository.TagRepo;
import com.example.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;
    @Autowired
    UserRepo userRepo;


    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    private TagDto tagDto = new TagDto();
    private Tag tag = new Tag();
    private User user = new User();
    private BaseUserModel baseUserModel;

    public List<Tag> findTag(String name) {
        return tagRepo.findTagByTagName(name);

    }

    public List<Tag> findTagByTagName(String tagName) {
        return tagRepo.findTagByTagName(tagName);
    }

    public List<Tag> findTagByLastName(String lastName) {
        return tagRepo.findTagByBaseUserModel_LastName(lastName);
    }

    public List<Tag> findTagByUserName(String name) {
        return tagRepo.findTagByBaseUserModel_Name(name);
    }

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }

    public List<Tag> putMethod(TagDto tagDto, long id) {
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

    public List<Tag> deleteMethod(String name, String tagName) {
        List<User> tmpUser = userService.findByName(name);
        userRepo.delete(tmpUser.get(0));
        //tmpUser.get(0).getTagList().removeIf(w -> w.getTagName().equals(tagName));
        //tmpUser.get(0).getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));
        //   tmpUser.get(0).getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

            userRepo.save(removetagFromAllLists(tmpUser.get(0) , tagName));
            tmpUser.remove(0);

        //List<Tag> tmpTag = tagRepo.findTagByTagName(tagName);
       // tagRepo.delete(tmpTag.get(0));
        return tagRepo.findAll();
    }

    public List<Tag> addTag(TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        List<User> tmp = userRepo.findUserByName(tag.getBaseUserModel().getName());
        user = tmp.get(0);
        user.addtoTagList(tag);
        userRepo.delete(tmp.get(0));
        tmp.remove(0);
        userRepo.save(user);
        return tagRepo.findAll();
    }

    private User removetagFromAllLists(User user , String tagName)
    {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }

    private TagDto convertEntityToDto(Tag tag) {
        tagDto = modelMapper.map(tag, TagDto.class);
        return tagDto;
    }

    private Tag convertDtoToEntity(TagDto tagDto) {
        tag = modelMapper.map(tagDto, Tag.class);
        return tag;
    }

    public BaseUserModel convertDtoToEntity(BaseUserDto baseUserDto) {
        baseUserModel = modelMapper.map(baseUserDto, BaseUserModel.class);
        return baseUserModel;
    }
}
