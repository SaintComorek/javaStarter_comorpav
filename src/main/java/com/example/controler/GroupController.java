package com.example.controler;

import com.example.dto.GroupDto;
import com.example.model.Group;
import com.example.repository.GroupRepo;
import com.example.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping()
    public List<Group> getAllGroup() {
        return groupService.getAllGroups();
    }
    @GetMapping("/find/user")
    public Group findUserGroup(@RequestParam String username , @RequestParam String groupname)
    {
        return groupService.findUserGroup(username,groupname);
    }
    @GetMapping("/findAll/user")
    public List<Group> findUserGroups(@RequestParam String username , @RequestParam String groupname)
    {
        return groupService.findUserGroups(username);
    }
    @GetMapping("/{id}")
    public Group findUserGroup(@PathVariable long id)
    {
        return groupService.findGroupById(id);
    }

    @PostMapping("/user")
    public Group createGroup(@RequestBody GroupDto groupDto, @RequestParam String username ) {
       return  groupService.createGroup(groupDto,username);
    }

    @PutMapping("/update/user")
    public Group updateTag(@RequestBody GroupDto groupDto,@RequestParam String username ,@RequestParam String groupname)
    {
      return groupService.updateGroup(groupDto,username,groupname);
    }
    @DeleteMapping("/delete/user")
    public void deleteGroup(@RequestParam String username ,@RequestParam String groupname)
    {
        groupService.deleteUserGroup(username,groupname);
    }
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable long id)
    {
        groupService.deleteById(id);
    }

}
