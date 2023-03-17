package com.example.controler;

import com.example.dto.NoteDto;
import com.example.dto.TagDto;
import com.example.dto.UserDto;
import com.example.model.Tag;
import com.example.model.User;
import com.example.repository.TagRepo;
import com.example.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class TagController {


    private final TagRepo tagRepo;

    private final TagService tagService;


    @GetMapping()
    public List<Tag> getAllNotes() {
        return tagRepo.findAll();
    }

    @PostMapping()
    public List<Tag> postNotes(@RequestBody TagDto tagDto) {
        tagService.addNote(tagDto);
        return tagRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tag> getNote(@PathVariable long id) {
        return tagRepo.findById(id);
    }

    @PutMapping("{id}")
    public List<Tag> putUsers(@RequestBody TagDto tagDto, @PathVariable Long id) {
        return tagService.putMethod(tagDto, id);

    }
}
