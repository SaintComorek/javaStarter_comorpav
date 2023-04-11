package com.example.service;

import com.example.dto.GroupDto;
import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GroupService {
    @Autowired
    GroupRepo groupRepo;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    private GroupDto groupDto;
    private Group group;
    private User user;


    public List<Group> getAllGroups() {
        return groupRepo.findAll();
    }

    public List<Group> findUserGroups(String username) {
        return groupRepo.findGroupByBaseUserModel_Username(username);
    }

    public Group findUserGroup(String username, String groupName) {
        return groupRepo.findByBaseUserModel_UsernameAndName(username, groupName);
    }

    public Group findGroupById(long id)
    {
        return groupRepo.findById(id);
    }

    public void deleteGroup(Group group) {
        groupRepo.delete(group);
    }

    public void deleteUserGroup(String username, String groupname) {
        groupRepo.delete(findUserGroup(username, groupname));
    }

    public void deleteById(long id) {
        deleteGroup(findGroupById(id));
    }
    public Group createGroup(GroupDto groupDto, String username) {
        group = convertDtoToEntity(groupDto);
        user = userService.findUser(username);
       if(user != null) {
           userService.deleteUser(user);
           user.addToGroupList(group);
           userService.saveUser(user);
           return findUserGroup(username, group.getName());
       }
       else
           return null;
    }

    public Group updateGroup(GroupDto groupDto, String username, String groupname)
    {
        group = findUserGroup(username,groupname);
        user = userService.findUser(username);
        if (user != null)
        {
            userService.deleteUser(user);
            user.getGroupList().remove(group);
            group = convertDtoToEntity(groupDto);
            user.getGroupList().add(group);
            userService.saveUser(user);
            return findUserGroup(username,group.getName());
        }
        else
            return null;
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
