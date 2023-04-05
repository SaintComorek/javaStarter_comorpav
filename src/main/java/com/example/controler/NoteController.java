package com.example.controler;

import com.example.dto.NoteDto;
import com.example.dto.TagDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.model.Tag;
import com.example.repository.NoteRepo;
import com.example.repository.TagRepo;
import com.example.service.NoteService;
import com.example.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepo noteRepo;

    private final NoteService noteService;

    @GetMapping()
    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    @PostMapping()
    public List<Note> postNote(@RequestBody NoteDto noteDto) {
        noteService.addNote(noteDto);
        return noteRepo.findAll();
    }

    @PostMapping("send/{groupName}")
    public List<Note> postNoteToGroup(@RequestBody NoteDto noteDto , @PathVariable String groupName ) {
        return  noteService.addNoteToGroup(noteDto , groupName);
    }

    @GetMapping("/find/name/{name}")
    public List<Note> gatNoteByUserName(@PathVariable String name) {

        return noteService.findNoteByUserName(name);
    }

    @GetMapping("/find/lastname/{lastName}")
    public List<Note> gatNoteByUserLastName(@PathVariable String lastName) {

        return noteService.findNoteByLastName(lastName);
    }
   /* @GetMapping("/find/tagname/{tagName}")
    public List<Note> getNoteByTagName(@PathVariable String tagName) {

        return noteService.findNote(tagName);
    }

    */

    @GetMapping("/{id}")
    public Optional<Note> getNote(@PathVariable long id) {
        return noteRepo.findById(id);
    }

    @PutMapping("{id}")
    public List<Note> putNote(@RequestBody NoteDto noteDto, @PathVariable Long id) {
        return noteService.putMethod(noteDto, id);
    }

    @DeleteMapping("{id}")
    public List<Note> deleteNote(@PathVariable Long id) {
        return noteService.deleteMethod(id);
    }

}
