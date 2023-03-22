package com.example.service;

import com.example.dto.GroupDto;
import com.example.model.Group;
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
    ModelMapper modelMapper;
    private GroupDto groupDto = new GroupDto();
    private Group group = new Group();

    public List<GroupDto> getAllGroups() {
        return this.groupRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
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

    public List<Group> addNote(GroupDto groupDto) {
        group = convertDtoToEntity(groupDto);
        groupRepo.save(group);
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
