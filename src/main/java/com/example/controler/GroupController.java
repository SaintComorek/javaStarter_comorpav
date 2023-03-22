package com.example.controler;

import com.example.dto.GroupDto;
import com.example.dto.NoteDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.repository.GroupRepo;
import com.example.repository.NoteRepo;
import com.example.service.GroupService;
import com.example.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        groupService.addNote(groupDto);
        return groupRepo.findAll();
    }

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
