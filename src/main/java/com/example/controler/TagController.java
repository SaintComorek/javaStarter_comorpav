package com.example.controler;

import com.example.dto.BaseUserDto;
import com.example.dto.TagDto;
import com.example.model.Tag;
import com.example.repository.TagRepo;
import com.example.service.TagService;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public Optional<Tag> getTag(@PathVariable long id) {
        return tagRepo.findById(id);
    }
    @GetMapping("/{tagName}")
    public List<Tag> getTag(@PathVariable String tagName) {

        return tagService.findTag(tagName);

    }


    @GetMapping("/find/{name}")
    public List<Tag> gatTagByUserName(@PathVariable String name) {

        return tagService.findTagByUserName(name);
    }

    @GetMapping("/find/{lastName}")
    public List<Tag> gatTagByUserLastName(@PathVariable String lastName) {

        return tagService.findTagByLastName(lastName);
    }

    @GetMapping("/find/{tagName}")
    public List<Tag> gatTagByTagName(@PathVariable String tagName) {

        return tagService.findTagByTagName(tagName);
    }
    @GetMapping("/find/{emailAddress}")
    public List<Tag> gatTagByUserEmailAddress(@PathVariable String emailAddress) {

        return tagService.findTagByUserEmailAddress(emailAddress);
    }

    @PostMapping()
    public List<Tag> postTag(@RequestBody TagDto tagDto) {
        tagService.addTag(tagDto);
        return tagRepo.findAll();
    }

    @PutMapping("{id}")
    public List<Tag> putTags(@RequestBody TagDto tagDto, @PathVariable Long id) {
        return tagService.putMethod(tagDto, id);
    }

    @DeleteMapping("{id}")
    public List<Tag> deleteTag(@PathVariable Long id) {
        return tagService.deleteMethod(id);
    }



    /*
    @GetMapping("/{id}")
    public Optional<Tag> getTag(@PathVariable long id) {
        return tagRepo.findById(id);
    }







    @GetMapping("/user")
    public List<Tag> getTagsOfUser(@RequestBody BaseUserDto baseUserDto) {
        return tagService.findTagByUser(baseUserDto);
    }

 */


}
