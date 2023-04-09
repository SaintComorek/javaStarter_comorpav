package com.example.service;

import com.example.dto.GroupDto;
import com.example.dto.TagDto;
import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    GroupRepo groupRepo;
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
    public List<Group> findUserGroups(String username)
    {
        return groupRepo.findGroupByBaseUserModel_Username(username);
    }

    public Group findUserGroup(String username, String groupName) {
        return groupRepo.findByBaseUserModel_UsernameAndName(username ,groupName);
    }

    public List<Group> addGroupToUser(GroupDto groupDto, String name, String lastname) {
        group = convertDtoToEntity(groupDto);
        List<User> tmpUser = userService.getUser(name, lastname);
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getGroupList().add(group);
        return tmpUser.remove(0).getGroupList();
    }


    public List<Group> editGroupTag(String username , String lastname , String groupname, String tagname, TagDto tagDto) {




       // group = groupRepo.findById(id);
        //if (optionalNote.isPresent()) {
         //   deleteMethod(id);
         //   group = convertDtoToEntity(groupDto);
          //  groupRepo.save(group);
       // }
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

    public List<Group> deleteMethod(String username, String groupName) {
        List<User> tmpUser = userService.findByName(username);
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getGroupList().removeIf(group -> group.getName().equals(groupName));
        userService.saveUser(tmpUser.get(0));
        tmpUser.remove(0);
        return groupRepo.findGroupByBaseUserModel_Name(username);
    }

    public List<Group> deleteGroupFromUser(GroupDto groupDto, String name, String lastname) {
        group = convertDtoToEntity(groupDto);
        List<User> tmpUser = userService.getUser(name, lastname);
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getGroupList().removeIf(w -> w.getName().equals(group.getName()));
        return tmpUser.remove(0).getGroupList();
    }

    public List<Group> addGroup(GroupDto groupDto) {
        group = convertDtoToEntity(groupDto);
        List<User> tmp = userService.findByName(group.getBaseUserModel().getName());
        user = tmp.get(0);
        userService.deleteUser(tmp.get(0));
        tmp.remove(0);
        user.addToGroupList(group);
        userService.saveUser(user);
        return user.getGroupList().stream().filter(w -> w.getName().equals(group.getName())).collect(Collectors.toList());
    }


    /*

    public List<Group> update(User user , String groupname ,String tagname ) {

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
     */

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

    // create check function for checking if group tag is  used in another group
                        /*
                        for (int i = 0; i < tmpTag.size(); i++) {
                            final int tmp = i;
                            tmpUser.get(0).getGroupList().stream().forEach(w -> w.getTags().forEach(tag -> {
                                if (tag.getTagName().equals(tmpTag.get(tmp))) {

                                    tmpTag.remove(tag);
                                }
                            }));
                        }

                         */

}
