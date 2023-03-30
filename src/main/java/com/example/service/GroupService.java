package com.example.service;

import com.example.dto.GroupDto;
import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepo;
import com.example.repository.UserRepo;
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
    UserRepo userRepo;
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
    public List<Group> findGroup(String name)
    {
        return groupRepo.findByTag_TagName(name);
    }
    public List<Group> findGroupByNameAndTag(String name , String tagName)
    {
        return groupRepo.findGroupByBaseUserModel_NameAndTag_TagName(name, tagName);
    }
    public List<Group> findGroupByUserName(String name)
    {
        return groupRepo.findGroupByBaseUserModel_Name(name);
    }
    public List<Group> findGroupByLastName(String lastName) {
        return groupRepo.findGroupByBaseUserModel_LastName(lastName);
    }
    /*


    public List<Group> findGroupByEmailAddress(String emailAddress) {
        return groupRepo.findGroupByBaseUserModel_EmailAddress(emailAddress);
    }

     */
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

    public List<Group> addGroup(GroupDto groupDto) {
        group = convertDtoToEntity(groupDto);
        List<User> tmp = userRepo.findUserByName(group.getBaseUserModel().getName());
        user = tmp.get(0);
        user.addToTagList(group.getTag());
        userRepo.delete(tmp.get(0));
        tmp.remove(0);
        user.addToGroupList(group);
        userRepo.save(user);
        return groupRepo.findAll();
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
