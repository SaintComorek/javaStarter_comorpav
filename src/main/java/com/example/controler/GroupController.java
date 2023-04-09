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

    //Path variable  methods
    @PutMapping("/update/tag/user")
    public String updateTag(@RequestParam String username , @RequestParam String lastname, @RequestParam String grupname, @RequestParam String tagname)
    {
        return "Hello world";


    }

    @GetMapping("/find/name/{name}")
    public List<Group> getGroupByUserName(@PathVariable String name) {

        return groupService.findGroupByUserName(name);
    }

    //Request Parameter methods

    @GetMapping("/find/user")
    public List<Group> findUserGroups(@RequestParam String name ,@RequestParam String lastname)
    {
        return findUserGroups(name , lastname);
    }

    @PostMapping("/add/group/user")
    public List<Group> addGroupToUser(@RequestBody GroupDto groupDto, @RequestParam String name , @RequestParam String lastname )
    {
        return addGroupToUser(groupDto , name , lastname);
    }


    @GetMapping("/find/group/user")
    public List<Group> getGroupByUserLastName(@RequestParam String lastName ) {

        return groupService.findGroupByLastName(lastName);
    }

    @GetMapping("/{id}")
    public Optional<Group> getGroup(@PathVariable long id) {
        return groupRepo.findById(id);
    }

    /*
    @PutMapping("{id}")
    public List<Group> putGroup(@RequestBody GroupDto groupDto, @PathVariable Long id) {
        return groupService.putMethod(groupDto, id);
    }

    @DeleteMapping("{id}")
    public List<Group> deleteGroup(@PathVariable Long id) {
        return groupService.deleteMethod(id);
    }
    @DeleteMapping("/remove/{name}/{tagname}")
    public void deleteGroup(@PathVariable String name ,  @PathVariable String tagname) {
        return groupService.deleteMethod(name, tagname);
    }

     */

}
