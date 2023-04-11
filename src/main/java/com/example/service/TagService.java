package com.example.service;

import com.example.dto.BaseUserDto;
import com.example.dto.TagDto;
import com.example.model.*;
import com.example.repository.GroupRepo;
import com.example.repository.TagRepo;
import com.example.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    private TagDto tagDto = new TagDto();
    private Tag tag = new Tag();
    private User user = new User();

    public List<Tag> findAllUserTags(String username) {
        return tagRepo.findByBaseUserModel_Username(username);

    }

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }

    public Tag findTag(String tagname, String username) {
        return tagRepo.findTagByTagNameAndBaseUserModel_Username(tagname, username);
    }

    public Tag findById(long id) {
        return tagRepo.findById(id);
    }

    public void deleteById(long id) {
        tagRepo.delete(findById(id));
    }

    public Tag deleteTag(String username, String tagname) {
        user = userService.findUser(username);
        userService.deleteUser(user);
        user.getGroupList().forEach(groups -> groups.getTags().removeIf(tags -> tags.getTagName().equals(tagname)));
        user.getNoteList().forEach(notes -> notes.getTags().removeIf(tags -> tags.getTagName().equals(tagname)));
        userService.saveUser(user);
        return findTag(tagname, user.getUsername());
    }

    public Tag deleteNoteTag(String username, String notename, String tagname) {
        user = userService.findUser(username);
        userService.deleteUser(user);
        user.getNoteList().forEach(notes -> {
            if (notes.getName().equals(notename)) {
                notes.getTags().forEach(tags -> {
                    if (tags.getTagName().equals(tagname)) {
                        notes.getTags().remove(tags);
                    }
                });
            }
        });
        userService.saveUser(user);
        return findTag(tagname, user.getUsername());
    }

    public Tag deleteGroupTag(String username, String groupname, String tagname) {
        user = userService.findUser(username);
        userService.deleteUser(user);
        user.getGroupList().forEach(groups -> {
            if (groups.getName().equals(groupname)) {
                groups.getTags().forEach(tags -> {
                    if (tags.getTagName().equals(tagname)) {
                        groups.getTags().remove(tags);
                    }
                });
            }
        });
        userService.saveUser(user);
        return findTag(tagname, user.getUsername());
    }

    public Tag createGroupTag(String username, String groupname, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        user = userService.findUser(username);
        userService.deleteUser(user);
        user.getGroupList().forEach(group -> {
            if (group.getName().equals(groupname)) {
                group.addTag(tag);
            }
        });
        return tag;
    }

    public Tag createNoteTag(String username, String notename, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        user = userService.findUser(username);
        userService.deleteUser(user);
        user.getNoteList().forEach(note -> {
            if (note.getName().equals(notename)) {
                note.addTag(tag);
            }
        });
        return tag;
    }


    public Tag updateTagFromGroup(String username, String groupname, String tagname, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getGroupList().forEach(group -> {
                if (group.getName().equals(groupname)) {
                    group.getTags().forEach(tags -> {
                        if (tags.getTagName().equals(tagname)) {
                            group.getTags().remove(tags);
                            group.getTags().add(tag);
                        }
                    });
                }
            });
            userService.saveUser(user);
            return findTag(tag.getTagName(), username);
        } else
            return null;
    }

    public Tag updateTagFromNote(String username, String notename, String tagname, TagDto tagDto) {
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getNoteList().forEach(notes -> {
                if (notes.getTags().equals(notename)) {

                    notes.getTags().forEach(tags -> {
                        if (tags.getTagName().equals(tagname)) {
                            notes.getTags().remove(tags);
                            notes.getTags().add(tag);
                        }
                    });
                }
            });
            userService.saveUser(user);
            return findTag(tag.getTagName(), username);
        } else
            return null;
    }


    private TagDto convertEntityToDto(Tag tag) {
        tagDto = modelMapper.map(tag, TagDto.class);
        return tagDto;
    }

    private Tag convertDtoToEntity(TagDto tagDto) {
        tag = modelMapper.map(tagDto, Tag.class);
        return tag;
    }
}
