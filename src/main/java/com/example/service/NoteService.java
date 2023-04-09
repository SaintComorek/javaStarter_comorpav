package com.example.service;

import com.example.dto.NoteDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.model.User;
import com.example.repository.GroupRepo;
import com.example.repository.NoteRepo;
import com.example.repository.UserRepo;
import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    GroupRepo groupRepo;
    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;
    @Autowired
    ModelMapper modelMapper;
    private NoteDto noteDto = new NoteDto();
    public Note note = new Note();

    public List<NoteDto> getAllNotesDtos() {
        return this.noteRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }


    public List<Note> getAllUserNotes(String name, String lastname) {
        return noteRepo.findByBaseUserModel_NameAndBaseUserModel_LastName(name, lastname);

    }

    public List<Note> findNoteById_list(long id) {
        return noteRepo.findById(id);
    }

    public Note findNoteId(long id) {
        return findNoteId(id);
    }


    public List<Note> findNoteByUserName(String name) {
        return noteRepo.findByBaseUserModel_Name(name);
    }

    public List<Note> findNoteByLastName(String lastName) {
        return noteRepo.findByBaseUserModel_LastName(lastName);
    }

    public Note findNote(String username , String groupname)
    {
        return  noteRepo.findByBaseUserModel_UsernameAndName(username , groupname);
    }
    public List<Note> findNotes(String username)
    {
        return noteRepo.findByBaseUserModel_Username(username);
    }

    public User updateNote(NoteDto noteDto, String username, String lastname, String noteName) {
        note = convertDtoToEntity(noteDto);
        List<User> tmpUser = userService.getUserAsList(username, lastname);
        userService.deleteUser(tmpUser.get(0));

        tmpUser.get(0).getNoteList().forEach(w -> {
            if (w.getName().equals(noteName)) {
                tmpUser.get(0).getNoteList().remove(w);
                tmpUser.get(0).getNoteList().add(note);
            }
        });

        tmpUser.get(0).getGroupList().forEach(group -> group.getNoteList().forEach(s -> {
            if (s.getName().equals(noteName)) {

                tmpUser.get(0).getGroupList().remove(group);
                group.getNoteList().remove(s);
                group.getNoteList().add(note);
                tmpUser.get(0).getGroupList().add(group);

            }
        }));

        userService.saveUser(tmpUser.get(0));
        return tmpUser.remove(0);
    }

    public Note updateById(NoteDto noteDto, long id) {
        noteRepo.delete(findNoteId(id));
        note = convertDtoToEntity(noteDto);
        noteRepo.save(note);
        return note;
    }

    public List<Note> deleteNoteFromNotes(String username, String tagName, String noteName) {
        List<User> tmpUser = userService.findByName(username);
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getNoteList().forEach(note -> note.getTags().forEach(tag -> {
            if (tag.getTagName().equals(tagName)) {

                tmpUser.get(0).getNoteList().remove(note);
            }
        }));
        return noteRepo.findAll();
    }

    public List<Group> deleteNoteFromGroup(String username, String lastname, String groupName, String noteName) {
        List<User> tmpUser = userService.findByName(username);
        userService.deleteUser(tmpUser.get(0));

        tmpUser.get(0).getGroupList().forEach(w -> {
            if (w.getName().equals(groupName)) {
                w.getNoteList().forEach(s -> {
                    if (s.getName().equals(noteName)) {

                        tmpUser.get(0).getGroupList().remove(w);
                        w.getNoteList().remove(s);
                        tmpUser.get(0).getGroupList().add(w);

                    }
                });
            }
        });

        userService.saveUser(tmpUser.get(0));
        return tmpUser.remove(0).getGroupList();
    }


    public void deleteNoteById(long id) {
        noteRepo.delete(findNoteId(id));
    }

    public List<Note> addNote(NoteDto noteDto, String name, String lastname) {
        note = convertDtoToEntity(noteDto);
        List<User> tmpUser = userService.getUserAsList(name, lastname);
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getNoteList().add(note);
        return tmpUser.remove(0).getNoteList();

/*
        List<User> tmp = userService.findByName(note.getBaseUserModel().getName());
        user = tmp.get(0);
        userRepo.delete(tmp.get(0));
        user.addToNoteList(note);
        user.getNoteTagList().addAll(note.getTags());
        user.getTagList().addAll(note.getTags());
        tmp.remove(0);
        userRepo.save(user);
        return noteRepo.findAll();

 */

    }

    public List<Group> addNoteToUserGroup(NoteDto noteDto, String username, String lastname, String groupname) {
        note = convertDtoToEntity(noteDto);
        List<User> tmpuser = userService.getUserAsList(username, lastname);
        userService.deleteUser(tmpuser.get(0));
        tmpuser.get(0).getNoteList().add(note);
        userService.saveUser(tmpuser.get(0));
        return tmpuser.remove(0).getGroupList();
    }

    public List<Group> addNoteToGroupsByTag(NoteDto noteDto, String username, String lastname, String grouptag) {

        note = convertDtoToEntity(noteDto);
        List<User> tmpUser = userService.findByName(note.getBaseUserModel().getName());
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getGroupList().stream().forEach(group -> group.getTags().forEach(tag -> {
            if (tag.getTagName().equals(grouptag)) {
                tmpUser.get(0).getGroupList().remove(group);
                group.addNote(note);
                tmpUser.get(0).getGroupList().add(group);
            }
        }));
/*
        userService.saveUser(tmpUser.get(0));
        tmpUser.remove(0);
        return noteRepo.findByBaseUserModel_Name(note.getBaseUserModel().getName());

 */
        userService.saveUser(tmpUser.get(0));
        List<Group> tagSelection = new ArrayList<>();
        tagSelection.clear();
        tmpUser.get(0).getGroupList().forEach(w -> w.getTags().forEach(s -> {
            if (s.getTagName().equals(grouptag)) {
                tagSelection.add(w);
            }
        }));

        return tagSelection;

    }

    public List<Group> addNoteToGroup(NoteDto noteDto, String username, String lastname, String groupname) {

        note = convertDtoToEntity(noteDto);
        List<User> tmpUser = userService.findByName(note.getBaseUserModel().getName());
        userService.deleteUser(tmpUser.get(0));
        tmpUser.get(0).getGroupList().stream().forEach(group -> {
            if (group.getName().equals(groupname)) {
                tmpUser.get(0).getGroupList().remove(group);
                group.getNoteList().add(note);
                tmpUser.get(0).getGroupList().add(group);

            }
        });
        /*
        userService.saveUser(tmpUser.get(0));
        tmpUser.remove(0);
        return noteRepo.findByBaseUserModel_Name(note.getBaseUserModel().getName());

         */

        userService.saveUser(tmpUser.get(0));
        return (List) tmpUser.get(0).getGroupList().stream().filter(w -> w.getName().equals(groupname));
    }


    private User removetagFromNoteList(User user, String tagName) {
        user.getTagList().removeIf(w -> w.getTagName().equals(tagName));
        user.getNoteTagList().removeIf(w -> w.getTagName().equals(tagName));

        return user;
    }


    private NoteDto convertEntityToDto(Note note) {
        noteDto = modelMapper.map(note, NoteDto.class);
        return noteDto;
    }

    private Note convertDtoToEntity(NoteDto noteDto) {
        note = modelMapper.map(noteDto, Note.class);
        return note;
    }
}
