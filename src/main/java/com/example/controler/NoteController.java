package com.example.controler;

import com.example.dto.NoteDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping()
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }
    @GetMapping("/get/id")
    public Note getNote(@RequestParam long id) {
        return noteService.findNoteId(id);
    }

    @GetMapping("/user")
    public List <Note> userNotes(@RequestParam String username) {
        return noteService.findNotes(username);
    }

    @GetMapping("/group/user")
    public List <Note> userGroupNotes(@RequestParam String username,@RequestParam String groupname) {
        return noteService.findGroupNotes(username,groupname);
    }

    @PostMapping("/create/user")
    public List<Note> createNote(@RequestBody NoteDto noteDto, @RequestParam String username)
    {
        return noteService.createNote(noteDto,username);
    }
    @PostMapping("/create/group/user")
    public List<Note> createNoteInGroup(@RequestBody NoteDto noteDto, @RequestParam String username, @RequestParam String groupname)
    {
        return noteService.createNote(username,groupname,noteDto);
    }


    @PutMapping("/update/user")
    public Note updateNote(@RequestBody NoteDto noteDto, @RequestParam String username, @RequestParam String notename) {
        return noteService.updateNote(noteDto,username, notename);
    }
    @PutMapping("/update/group/user")
    public Group updateNoteInGroup(@RequestBody NoteDto noteDto, @RequestParam String username, @RequestParam String groupname, @RequestParam String notename) {
        return noteService.updateNote(noteDto,username,groupname, notename);
    }
    @PutMapping("/update/{id}")
    public Note updateNoteById(@RequestBody NoteDto noteDto, @PathVariable long id) {
        return noteService.updateById(noteDto,id);
    }
    @DeleteMapping("/delete/user")
    public void deleteNote(@RequestParam String username, @RequestParam String notename) {
        noteService.deleteNote(username,notename);

    }

    @DeleteMapping("/delete/group/user")
    public void deleteNoteFromGroup(@RequestParam String username, @RequestParam String groupname, @RequestParam String notename) {
        noteService.deleteNoteFromGroup(username, groupname, notename);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotebyId(@PathVariable long id) {
        noteService.deleteNoteById(id);
    }

    @DeleteMapping("/delete/id")
    public void deleteNote(@RequestParam long id) {
        noteService.deleteNoteById(id);
    }

}
