package com.example.controler;

import com.example.dto.TagDto;
import com.example.model.Tag;
import com.example.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping()
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag findTag(@PathVariable long id) {
        return tagService.findById(id);
    }


    @GetMapping("/find/user")
    public Tag findTag(@RequestParam String username, @RequestParam String tagname) {

        return tagService.findTag(tagname, username);
    }

    @PostMapping("/create/group/user")
    public Tag createTagInGroup(@RequestParam String username, @RequestParam String groupname, @RequestBody TagDto tagDto) {
        return tagService.createGroupTag(username, groupname, tagDto);
    }

    @PostMapping("/create/note/user")
    public Tag createTagInNote(@RequestParam String username, @RequestParam String notename, @RequestBody TagDto tagDto) {
        return tagService.createNoteTag(username, notename, tagDto);
    }

    @PutMapping("/update/group/user")
    public Tag updateTagInGroup(@RequestParam String username, @RequestParam String groupname, @RequestParam String tagname, @RequestBody TagDto tagDto) {
        return tagService.updateTagFromGroup(username, groupname, tagname, tagDto);
    }

    @PutMapping("/update/note/user")
    public Tag updateTagInNote(@RequestParam String username, @RequestParam String notename, @RequestParam String tagname, @RequestBody TagDto tagDto) {
        return tagService.updateTagFromNote(username, notename, tagname, tagDto);
    }


    @DeleteMapping("/delete/user")
    public void deleteTag(@RequestParam String username, @RequestParam String tagname) {
        tagService.deleteTag(username, tagname);
    }

    @DeleteMapping("/delete/group/user")
    public void deleteTagFromGroup(@RequestParam String username, @RequestParam String groupname, @RequestParam String tagname) {
        tagService.deleteGroupTag(username, groupname, tagname);
    }

    @DeleteMapping("/delete/note/user")
    public void deleteTagFromNote(@RequestParam String username, @RequestParam String notename, @RequestParam String tagname) {
        tagService.deleteGroupTag(username, notename, tagname);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTagById(@PathVariable long id) {
        tagService.deleteById(id);
    }

}
