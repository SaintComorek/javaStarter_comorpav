package com.example.controler;

import com.example.dto.GroupDto;
import com.example.model.Group;
import com.example.repository.GroupRepo;
import com.example.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepo groupRepo;
    private final GroupService groupService;

    @GetMapping()
    public List<Group> getAllGroup() {
        return groupRepo.findAll();
    }

    @PostMapping()
    public List<Group> postGroup(@RequestBody GroupDto groupDto) {
        groupService.addGroup(groupDto);
        return groupRepo.findAll();
    }

    @GetMapping("/find/{name}")
    public List<Group> getGroupByUserName(@PathVariable String name) {

        return groupService.findGroupByUserName(name);
    }

    @GetMapping("/find/{lastName}")
    public List<Group> getGroupByUserLastName(@PathVariable String lastName) {

        return groupService.findGroupByLastName(lastName);
    }
    @GetMapping("/find/{tagName}")
    public List<Group> getGroupByTagName(@PathVariable String tagName) {

        return groupService.findGroup(tagName);
    }
    /*
    @GetMapping("/find/{emailAddress}")
    public List<Group> gatGroupByUserEmailAddress(@PathVariable String emailAddress) {

        return groupService.findGroupByEmailAddress(emailAddress);
    }

     */

    @GetMapping("/{id}")
    public Optional<Group> getGroup(@PathVariable long id) {
        return groupRepo.findById(id);
    }

    @PutMapping("{id}")
    public List<Group> putGroup(@RequestBody GroupDto groupDto, @PathVariable Long id) {
        return groupService.putMethod(groupDto, id);
    }

    @DeleteMapping("{id}")
    public List<Group> deleteGroup(@PathVariable Long id) {
        return groupService.deleteMethod(id);
    }

}
