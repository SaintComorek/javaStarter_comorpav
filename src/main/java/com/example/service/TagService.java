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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        tag = convertDtoToEntity(tagDto);
        //userService.getListOfUsers().stream().filter(w -> w.)
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
        removetagFromAllLists(tmpUser.get(0),tagName );
        userRepo.save(removetagFromAllLists(tmpUser.get(0), tagName));
        tmpUser.remove(0);

        return tagRepo.findAll();
    }

    public  List<Tag> updateTag(TagDto tagDto, String tagName)
    {
        tag = convertDtoToEntity(tagDto);
        List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
        userRepo.delete(tmpUser.get(0));
        updateGroupTag( tagDto,  tagName);
        updateNoteTag( tagDto,  tagName);
        tmpUser.get(0).getTagList().stream()
                .forEach(w -> { if (w.getTagName().equals(tagName))
                {
                    tmpUser.get(0).getTagList().remove(w);
                    w = tag;
                    tmpUser.get(0).addtoTagList(w);
                }});

        tmpUser.add(removetagFromAllLists(tmpUser.remove(0), tagName));
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
    }

    public List<Tag> updateGroupTag(TagDto tagDto, String tagName) {
        tag = convertDtoToEntity(tagDto);
        List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
        userRepo.delete(tmpUser.get(0));
        tmpUser.get(0).getGroupList().stream()
                        .forEach(w -> { if (w.getTag().getTagName().equals(tagName))
                        {
                            tmpUser.get(0).getGroupList().remove(w);
                            w.setTag(tag);
                            tmpUser.get(0).addToGroupList(w);
                        }});


        tmpUser.add(removetagFromGroupList(tmpUser.remove(0), tagName));
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
    }

    public List<Tag> updateNoteTag(TagDto tagDto , String tagName )
    {
        tag = convertDtoToEntity(tagDto);
        List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
        userRepo.delete(tmpUser.get(0));
        tmpUser.get(0).getNoteList().stream()
                .forEach(w -> { if (w.getTag().getTagName().equals(tagName))
                {
                    tmpUser.get(0).getNoteList().remove(w);
                    w.setTag(tag);
                    tmpUser.get(0).addToNoteList(w);
                }});
        tmpUser.add(removetagFromNoteList(tmpUser.remove(0), tagName));
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
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

    private User removetagFromAllLists(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }
    private User removetagFromGroupList(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }
    private User removetagFromNoteList(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));

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
