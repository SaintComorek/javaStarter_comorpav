package com.example.service;

import com.example.dto.BaseUserDto;
import com.example.dto.TagDto;
import com.example.model.*;
import com.example.repository.TagRepo;
import com.example.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;
    @Autowired
    NoteService noteService;

    @Autowired
    ModelMapper modelMapper;

    private TagDto tagDto = new TagDto();
    private Tag tag = new Tag();
    private User user = new User();
    private BaseUserModel baseUserModel;

    public List<Tag> findTag(String name) {
        return tagRepo.findTagByTagName(name);

    }

    public List<Tag> findTagByTagName(String tagName) {
        return tagRepo.findTagByTagName(tagName);
    }

    public List<Tag> findTagByLastName(String lastName) {
        return tagRepo.findTagByBaseUserModel_LastName(lastName);
    }

    public List<Tag> findTagByUserName(String name) {
        return tagRepo.findTagByBaseUserModel_Name(name);
    }

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }

    public List<Tag> putMethod(TagDto tagDto, long id) {
        tag = convertDtoToEntity(tagDto);
        //userService.getListOfUsers().stream().filter(w -> w.)
        Optional<Tag> optionalTag = tagRepo.findById(id);
        if (optionalTag.isPresent()) {
            deleteMethod(id);
            tag = convertDtoToEntity(tagDto);
            tagRepo.save(tag);
        }
        return tagRepo.findAll();
    }

    public List<Tag> deleteMethod(long id) {
        Optional<Tag> optionalTag = tagRepo.findById(id);
        if (optionalTag.isPresent()) {
            tag = optionalTag.get();
            tagRepo.delete(tag);
        }
        return tagRepo.findAll();
    }

    public List<Tag> deleteMethod(String name, String tagName) {
        List<User> tmpUser = userService.findByName(name);
        userRepo.delete(tmpUser.get(0));
        removetagFromAllLists(tmpUser.get(0), tagName);
        userRepo.save(removetagFromAllLists(tmpUser.get(0), tagName));
        tmpUser.remove(0);

        return tagRepo.findAll();
    }


    /*
    public List<Tag> updateTag(TagDto tagDto, String tagName) {
        tag = convertDtoToEntity(tagDto);
        List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
        userRepo.delete(tmpUser.get(0));
        updateGroupTag(tagDto, tagName);
        updateNoteTag(tagDto, tagName);
        tmpUser.get(0).getTagList().stream()
                .forEach(w -> {
                    if (w.getTagName().equals(tagName)) {
                        tmpUser.get(0).getTagList().remove(w);
                        w = tag;
                        tmpUser.get(0).addtoTagList(w);
                    }
                });

        tmpUser.add(removetagFromAllLists(tmpUser.remove(0), tagName));
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
    }

     */

    public List<Tag> updateGroupTag(String grupName, TagDto tagDto, String tagName) {
        tag = convertDtoToEntity(tagDto);
        List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getGroupList().stream()
                .forEach(group -> {
                    if (group.getName().equals(grupName)) {
                        group.getTags().forEach(tags -> {
                            if (tags.getTagName().equals(tagName)) {
                                tmpUser.get(0).getGroupList().remove(group);
                                group.getTags().remove(tags);
                                tmpUser.get(0).getGroupList().add(group);
                            }
                        });

                    }
                });

/*
        tmpUser.get(0).getGroupList().stream()
                .forEach(w -> {
                    if (w.getTag().getTagName().equals(tagName)) {
                        tmpUser.get(0).getGroupList().remove(w);
                        w.setTag(tag);
                        tmpUser.get(0).addToGroupList(w);
                    }
                });

 */


        tmpUser.add(removetagFromGroupList(tmpUser.remove(0), tagName));
        userService.saveUser(tmpUser.get(0));
        tmpUser.remove(0);
        return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
    }

    public List<Tag> updateNoteTag(TagDto tagDto, String tagName) {
        tag = convertDtoToEntity(tagDto);
        List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
        List<Note> tmpNote = new ArrayList<>();
        List<Tag> tmpTag = new ArrayList<>();
        userRepo.delete(tmpUser.get(0));

        tmpNote = tmpUser.get(0).getNoteList();
        // tmpUser.remove(tmpNote);
        List<List<Tag>> taglist = tmpNote.stream().map(w -> w.getTags()).collect(Collectors.toList());
        //.forEach(sublist -> sublist.forEach(element -> System.out.println(element)));

        List<Note> finalTmpNote = tmpNote;

        tmpUser.get(0).getNoteList().forEach(note -> note.getTags().forEach(e -> {
            if (e.getTagName().equals(tagName)) {
                tmpUser.get(0).getNoteList().remove(note);
                note.getTags().remove(e);
                note.addToTagList(tag);
                tmpUser.get(0).addToNoteList(note);
                tmpUser.get(0).addToNoteTagList(tag);
                removetagFromNoteList(tmpUser.get(0), tagName);

            }
        }));

        tmpUser.add(removetagFromNoteList(tmpUser.remove(0), tagName));
        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
    }


    /*
        public List<Tag> updateNoteTag(TagDto tagDto , String tagName )
        {
            tag = convertDtoToEntity(tagDto);
            List<User> tmpUser = userService.findByName(tag.getBaseUserModel().getName());
            userRepo.delete(tmpUser.get(0));
            tmpUser.get(0).getNoteList().stream()
                    .forEach(w -> { if (w.getTag().getTagName().equals(tagName))
                    {
                        tmpUser.get(0).getNoteList().remove(w);
                        w.setTag(tag);
                        tmpUser.get(0).addToNoteList(w);
                    }});

            tmpUser.add(removetagFromNoteList(tmpUser.remove(0), tagName));
            userRepo.save(tmpUser.get(0));
            tmpUser.remove(0);
            return tagRepo.findTagByBaseUserModel_Name(tag.getBaseUserModel().getName());
        }

     */
    public List<Tag> addTag(TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        userService.findUser(tag.getBaseUserModel().getUsername()).getTagList().add(tag);
        return userService.findUser(tag.getBaseUserModel().getUsername()).getTagList();
    }

    public Group addTagToGroup(String username, String groupName, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);/*
        user = userService.findUser(username);
        user.getGroupList().stream().forEach(group -> group.getTags().forEach( tags -> { if(!tags.getTagName().equals(tag.getTagName())){

            user.getGroupList().remove(group);
            group.getTags().add(tag);
            user.getGroupList().add(group);

        }} ));
        */
        groupService.findUserGroup(username, groupName).addTag(tag);
        return groupService.findUserGroup(username, groupName);

    }

    public Note addTagToNote(String username, String notename, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);/*
        user = userService.findUser(username);
        user.getGroupList().stream().forEach(group -> group.getTags().forEach( tags -> { if(!tags.getTagName().equals(tag.getTagName())){

            user.getGroupList().remove(group);
            group.getTags().add(tag);
            user.getGroupList().add(group);

        }} ));
        */
        noteService.findNote(username, notename).addToTagList(tag);
        return noteService.findNote(username, notename);

    }

    public void deleteTagFromNote(String username, String notename, String tagname) {
        removetagFromNoteList(userService.findUser(username), tagname);
        noteService.findNote(username, notename).getTags().removeIf(tag -> tag.getTagName().equals(tagname));
    }

    public void deleteTagFromGroup(String username, String groupname, String tagname) {
        removetagFromGroupList(userService.findUser(username), tagname);
        groupService.findUserGroup(username, groupname).getTags().removeIf(tag -> tag.getTagName().equals(tagname));
    }

    public void deleteTagFromNotes(String username, String tagname) {
        removetagFromNoteList(userService.findUser(username), tagname);
        noteService.findNotes(username).forEach(notes -> notes.getTags().removeIf(tag -> tag.getTagName().equals(tagname)));
    }

    public void deleteTagFromGroups(String username, String tagname) {
        removetagFromGroupList(userService.findUser(username), tagname);
        groupService.findUserGroups(username).forEach(group -> group.getTags().removeIf(tag -> tag.getTagName().equals(tagname)));
    }

    public void deleteTag(String username, String tagname) {

        removetagFromAllLists(userService.findUser(username), tagname);
        deleteTagFromGroups(username, tagname);
        deleteTagFromNotes(username, tagname);
        // groupService.findUserGroups(username).forEach(group -> group.getTags().removeIf(tags -> tags.getTagName().equals(tagname)));
        //  noteService.findNotes(username).forEach(note -> note.getTags().removeIf(tags -> tags.getTagName().equals(tagname)));
    }

    public Tag createTag(String username, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        userService.findUser(username).addtoTagList(tag);
        return tag;
    }

    public List<Group> updateTagFromGroups(String username, String tagname, TagDto tagDto) {
        groupService.findUserGroups(username).forEach(group -> group.getTags().forEach(tag -> {
            if (tag.equals(tagname)) {
                tag.setTagName(tagDto.getTagName());
            }
        }));
        removetagFromGroupList(userService.findUser(username), tagname);
        addTagToUserGroupList(userService.findUser(username), tagDto);
        return groupService.findUserGroups(username);
    }

    public List<Note> updateTagFromNotes(String username, String tagname, TagDto tagDto) {
        noteService.findNotes(username).forEach(note -> note.getTags().forEach(tag -> {
            if (tag.equals(tagname)) {
                tag.setTagName(tagDto.getTagName());
            }
        }));
        removetagFromNoteList(userService.findUser(username), tagname);
        addTagToUserNoteList(userService.findUser(username), tagDto);
        return noteService.findNotes(username);
    }


    public User updateTag(String username, String tagname, TagDto tagDto) {
        //deleteTag(username, tagname);
        updateTagFromGroups(username, tagname, tagDto);
        updateTagFromNotes(username, tagname, tagDto);
        userService.findUser(username).getTagList().forEach(tag -> {
            if (tag.getTagName().equals(tagname)) {
                tag.setTagName(tagDto.getTagName());
            }
        });
        return userService.findUser(username);
    }


    private User removetagFromAllLists(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }

    private User removetagFromGroupList(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getGroupTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }

    private User removetagFromNoteList(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }

    private void addTagToUserNoteList(User user, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        user.getTagList().add(tag);
        user.getNoteTagList().add(tag);
    }

    private void addTagToUserGroupList(User user, TagDto tagDto) {
        tag = convertDtoToEntity(tagDto);
        user.getTagList().add(tag);
        user.getGroupTagList().add(tag);
    }

    private TagDto convertEntityToDto(Tag tag) {
        tagDto = modelMapper.map(tag, TagDto.class);
        return tagDto;
    }

    private Tag convertDtoToEntity(TagDto tagDto) {
        tag = modelMapper.map(tagDto, Tag.class);
        return tag;
    }
}
