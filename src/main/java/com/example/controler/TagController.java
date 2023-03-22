package com.example.controler;

import com.example.dto.TagDto;
import com.example.model.Tag;
import com.example.repository.TagRepo;
import com.example.service.TagService;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagRepo tagRepo;

    private final TagService tagService;

    @GetMapping()
    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }

    @PostMapping()
    public List<Tag> postTag(@RequestBody TagDto tagDto) {
        tagService.addTag(tagDto);
        return tagRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tag> getTag(@PathVariable long id) {
        return tagRepo.findById(id);
    }

    @PutMapping("{id}")
    public List<Tag> putTags(@RequestBody TagDto tagDto, @PathVariable Long id) {
        return tagService.putMethod(tagDto, id);
    }

    @DeleteMapping("{id}")
    public List<Tag> deleteTag(@PathVariable Long id) {
        return tagService.deleteMethod(id);
    }
}
