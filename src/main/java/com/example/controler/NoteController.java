package com.example.controler;

import com.example.dto.NoteDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.model.User;
import com.example.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping()
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/user/allnotes")
    public List<Note> getAllUserNotes(@RequestParam String name, @RequestParam String lastname) {
        return noteService.getAllUserNotes(name, lastname);
    }

    @PutMapping("/user/update")
    public User updateNote(@RequestBody NoteDto noteDto, @RequestParam String name, @RequestParam String lastname, @RequestParam String notename) {
        return noteService.updateNote(noteDto, name, lastname, notename);
    }


    @PostMapping("/user/groups")
    public List<Group> addNoteToMultipleUserGroups(@RequestBody NoteDto noteDto, @RequestParam String name, @RequestParam String lastname, @RequestParam String grouptag) {

        return noteService.addNoteToGroupsByTag(noteDto, name, lastname, grouptag);
    }

    @PostMapping("/add/user/")
    public List<Group> addNoteToUser(@RequestBody NoteDto noteDto, @RequestParam String name, @RequestParam String lastname, @RequestParam String groupname) {
        return noteService.addNoteToUserGroup(noteDto, name, lastname, groupname);
    }

    @PostMapping("/add/note/group/user")
    public List<Group> addNoteToGroup(NoteDto noteDto, @RequestParam String name, @RequestParam String lastname, @RequestParam String groupname) {
        return noteService.addNoteToUserGroup(noteDto, name, lastname, groupname);
    }
    // Path variable using

    @GetMapping("/find/notes/name")
    public List<Note> gatNoteByUserName(@RequestParam String name) {

        return noteService.findNoteByUserName(name);
    }

    @GetMapping("/find/notes/lastname")
    public List<Note> gatNoteByUserLastName(@RequestParam String lastName) {

        return noteService.findNoteByLastName(lastName);
    }

    @DeleteMapping("/delete/user")
    public void deleteNoteFromNotes(@RequestParam String name  ,  @RequestParam String lastname ,  @RequestParam String notename)
    {
        noteService.deleteNoteFromNotes(name , lastname , notename);

    }

    @DeleteMapping("/delete/user/group")
    public void deleteNoteFromGroup(@RequestParam String name  ,  @RequestParam String lastname , @RequestParam String groupname  ,  @RequestParam String notename)
    {
        noteService.deleteNoteFromGroup(name , lastname , groupname , notename);

    }

    @GetMapping("/get/id")
    public Note getNote(@RequestParam long id) {
        return noteService.findNoteId(id);
    }

    @PutMapping("/upt/id")
    public Note putNote(@RequestBody NoteDto noteDto, @RequestParam long id) {
        return noteService.updateById(noteDto, id);
    }

    @DeleteMapping("/delete/id")
    public void deleteNote(@RequestParam long id) {
        noteService.deleteNoteById(id);
    }

}
