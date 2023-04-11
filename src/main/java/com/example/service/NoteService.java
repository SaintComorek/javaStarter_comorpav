package com.example.service;

import com.example.dto.NoteDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.model.User;
import com.example.repository.NoteRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;
    @Autowired
    ModelMapper modelMapper;
    private NoteDto noteDto = new NoteDto();
    private Note note;
    private User user;
    private Group group;


    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    public Note findNoteId(long id) {
        return findNoteId(id);
    }

    public Note findUserNote(String notename, String username) {
        return noteRepo.findByNameAndBaseUserModel_Username(notename, username);
    }

    public Note findNote(String username, String notename) {
        return noteRepo.findByNameAndBaseUserModel_Username(notename, username);
    }


    public List<Note> findGroupNotes(String username, String groupname) {
        group = groupService.findUserGroup(username, groupname);
        if (group != null) {
            return group.getNoteList();
        } else
            return Collections.emptyList();
    }

    public List<Note> findNotes(String username) {
        return noteRepo.findByBaseUserModel_Username(username);
    }

    public Note updateNote(NoteDto noteDto, String username, String notename) {
        note = convertDtoToEntity(noteDto);
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getNoteList().removeIf(notes -> notes.getName().equals(notename));
            user.getNoteList().add(note);
            userService.saveUser(user);
            return findNote(username, notename);
        } else
            return null;
    }

    public Group updateNote(NoteDto noteDto, String username, String groupname, String notename) {
        note = convertDtoToEntity(noteDto);
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getGroupList().forEach(group -> {
                if (group.getName().equals(groupname)) {
                    user.getGroupList().remove(group);
                    group.getNoteList().removeIf(notes -> notes.getName().equals(notename));
                    group.addNote(note);
                    user.getGroupList().add(group);
                    userService.saveUser(user);
                }
            });
            return groupService.findUserGroup(username, groupname);
        } else
            return null;
    }


    public Note updateById(NoteDto noteDto, long id) {
        noteRepo.delete(findNoteId(id));
        note = convertDtoToEntity(noteDto);
        noteRepo.save(note);
        return note;
    }

    public Note deleteNote(String username, String notename) {
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getNoteList().removeIf(note -> note.getName().equals(notename));
            return findUserNote(notename, username);
        }
        return null;
    }

    public Group deleteNoteFromGroup(String username, String groupName, String noteName) {
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getGroupList().forEach(group -> {
                if (group.getName().equals(groupName)) {
                    group.getNoteList().forEach(note -> {
                        if (note.getName().equals(noteName)) {
                            group.getNoteList().remove(note);
                        }
                    });
                }
            });
            userService.saveUser(user);
            return groupService.findUserGroup(username, groupName);
        }
        return null;
    }


    public void deleteNoteById(long id) {
        noteRepo.delete(findNoteId(id));
    }

    public List<Note> createNote(NoteDto noteDto, String username) {
        note = convertDtoToEntity(noteDto);
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getNoteList().add(note);
            userService.saveUser(user);
            return user.getNoteList();
        } else
            return Collections.emptyList();
    }

    public List<Note> createNote(String username, String groupname, NoteDto noteDto) {
        note = convertDtoToEntity(noteDto);
        user = userService.findUser(username);
        if (user != null) {
            userService.deleteUser(user);
            user.getGroupList().forEach(groups -> {
                if (groups.getName().equals(groupname)) {
                    groups.addNote(note);
                }
            });
            user.getNoteList().add(note);
            userService.saveUser(user);
            return groupService.findUserGroup(username, groupname).getNoteList();
        } else
            return Collections.emptyList();
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
