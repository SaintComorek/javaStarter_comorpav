package com.example.controler;

import com.example.dto.NoteDto;
import com.example.dto.TagDto;
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
    public List<Note> getAllTags() {
        return noteRepo.findAll();
    }

    @PostMapping()
    public List<Note> postTag(@RequestBody NoteDto noteDto) {
        noteService.addNote(noteDto);
        return noteRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Note> getNote(@PathVariable long id) {
        return noteRepo.findById(id);
    }

    @PutMapping("{id}")
    public List<Note> putNote(@RequestBody NoteDto noteDto, @PathVariable Long id) {
        return noteService.putMethod(noteDto, id);
    }

    @DeleteMapping("{id}")
    public List<Note> deleteTag(@PathVariable Long id) {
        return noteService.deleteMethod(id);
    }


}
