package com.example.service;

import com.example.dto.GroupDto;
import com.example.model.Group;
import com.example.model.Tag;
import com.example.model.User;
import com.example.repository.GroupRepo;
import com.example.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    GroupRepo groupRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    private GroupDto groupDto = new GroupDto();
    private Group group = new Group();
    private User user = new User();


    public List<GroupDto> getAllGroups() {
        return this.groupRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<Group> findGroupByUserName(String name) {
        return groupRepo.findGroupByBaseUserModel_Name(name);
    }

    public List<Group> findGroupByLastName(String lastName) {
        return groupRepo.findGroupByBaseUserModel_LastName(lastName);
    }

    public List<Group> putMethod(GroupDto groupDto, long id) {
        Optional<Group> optionalNote = groupRepo.findById(id);
        if (optionalNote.isPresent()) {
            deleteMethod(id);
            group = convertDtoToEntity(groupDto);
            groupRepo.save(group);
        }
        return groupRepo.findAll();
    }

    public List<Group> deleteMethod(long id) {
        Optional<Group> optionalNote = groupRepo.findById(id);
        if (optionalNote.isPresent()) {
            group = optionalNote.get();
            groupRepo.delete(group);
        }
        return groupRepo.findAll();
    }

    public List<Group> deleteMethod(String username, String name) {
        List<User> tmpUser = userService.findByName(username);

        tmpUser.get(0).getGroupList().stream()
                .forEach(group -> {
                    if (group.getName().equals(name)) {
                        tmpUser.get(0).getGroupList().remove(group);
                        List<Tag> tmpTag = group.getTags();
                        // create check function for checking if group tag is  used in another group
                        for (int i = 0; i < tmpTag.size(); i++) {
                            final int tmp = i;
                            tmpUser.get(0).getGroupList().stream().forEach(w -> w.getTags().forEach(tag -> {
                                if (tag.getTagName().equals(tmpTag.get(tmp))) {

                                    tmpTag.remove(tag);
                                }
                            }));
                        }
                        tmpUser.get(0).getGroupTagList().remove(tmpTag);
                    }
                });
        userService.saveUser(tmpUser.get(0));
        tmpUser.remove(0);
        return groupRepo.findGroupByBaseUserModel_Name(username);
/*
        userRepo.delete(tmpUser.get(0));
        tmpUser.get(0).getGroupList().remove(tmpGroup.get(0));
        tmpUser.get(0).getTagList().removeIf(w -> w.getTagName().equals(tagname));
        tmpUser.get(0).getGroupTagList().removeIf(w -> w.getTagName().equals(tagname));
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        tmpGroup.remove(0);

 */
        // return groupRepo.findAll();
    }

    public boolean checkTagsUsing(List<Tag> tagList, List<Group> groupsList) {

        groupsList.stream().map(group -> {
            if (group.getTags().contains(tagList.stream())) {
                return true;
            }
            return false;
        });

        return false;
    }

    public List<Group> addGroup(GroupDto groupDto) {
        group = convertDtoToEntity(groupDto);
        List<User> tmp = userRepo.findUserByName(group.getBaseUserModel().getName());
        user = tmp.get(0);
        userRepo.delete(tmp.get(0));
        tmp.remove(0);
        user.addToGroupList(group);
        userRepo.save(user);
        return groupRepo.findAll();
    }


    public List<Group> update(GroupDto groupDto, String tagname) {
        group = convertDtoToEntity(groupDto);
        List<User> tmpUser = userService.findByName(group.getBaseUserModel().getName());
      //  List<Group> tmpGroup = findGroupByNameAndTag(group.getBaseUserModel().getName(), tagname);
        userRepo.delete(tmpUser.get(0));
        //tmpUser.get(0).getGroupList().remove(tmpGroup.get(0));
        tmpUser.get(0).addToGroupList(group);
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
      //  tmpGroup.remove(0);


        return groupRepo.findAll();
    }

    private User removeGroupFromAllLists(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }


    private GroupDto convertEntityToDto(Group group) {
        groupDto = modelMapper.map(group, GroupDto.class);
        return groupDto;
    }

    private Group convertDtoToEntity(GroupDto groupDto) {
        group = modelMapper.map(groupDto, Group.class);
        return group;
    }

}
