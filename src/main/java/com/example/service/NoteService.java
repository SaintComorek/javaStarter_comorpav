package com.example.service;

import com.example.dto.NoteDto;
import com.example.model.Group;
import com.example.model.Note;
import com.example.model.User;
import com.example.repository.GroupRepo;
import com.example.repository.NoteRepo;
import com.example.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    GroupRepo groupRepo;

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    private NoteDto noteDto = new NoteDto();
    public Note note = new Note();
    private User user = new User();
    private Group group = new Group();

    public List<NoteDto> getAllNotes() {
        return this.noteRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<Note> findNote(String name) {
        return noteRepo.findByTag_TagName(name);
    }

    public List<Note> findNoteByUserName(String name) {
        return noteRepo.findByBaseUserModel_Name(name);
    }

    public List<Note> findNoteByLastName(String lastName) {
        return noteRepo.findByBaseUserModel_LastName(lastName);
    }

    /*
    public List<Note> findNoteByEmailAddress(String emailAddress) {
        return noteRepo.findByBaseUserModel_EmailAddress(emailAddress);
    }

     */
    public List<Note> putMethod(NoteDto noteDto, long id) {
        Optional<Note> optionalNote = noteRepo.findById(id);
        if (optionalNote.isPresent()) {
            deleteMethod(id);
            note = convertDtoToEntity(noteDto);
            noteRepo.save(note);
        }
        return noteRepo.findAll();
    }

    public List<Note> deleteMethod(long id) {
        Optional<Note> optionalNote = noteRepo.findById(id);
        if (optionalNote.isPresent()) {
            note = optionalNote.get();
            noteRepo.delete(note);
        }
        return noteRepo.findAll();
    }
    /*
    public List<Note> deleteMethod(String name , String tagName) {
        Optional<Note> optionalNote = noteRepo.findById(id);
        if (optionalNote.isPresent()) {
            note = optionalNote.get();
            noteRepo.delete(note);
        }
        return noteRepo.findAll();
    }

     */

    /*
        public List<Note> addNote(NoteDto noteDto) {
            note = convertDtoToEntity(noteDto);
            noteRepo.save(note);
            return noteRepo.findAll();
        }

     */
    public List<Note> addNote(NoteDto noteDto) {
        note = convertDtoToEntity(noteDto);
        List<User> tmp = userService.findByName(note.getBaseUserModel().getName());
        user = tmp.get(0);
        userRepo.delete(tmp.get(0));
        tmp.remove(0);
        user.addToNoteList(note);
        userRepo.save(user);
        return noteRepo.findAll();

    }


    public List<Note> addNoteToGroup(NoteDto noteDto, String groupTag) {

        note = convertDtoToEntity(noteDto);
        List<User> tmpUser = userService.findByName(note.getBaseUserModel().getName());
        userRepo.delete(tmpUser.get(0));
        tmpUser.get(0).getGroupList().stream()
                .forEach(w -> {if (w.getTag().getTagName().equals(groupTag)) {

                    tmpUser.get(0).getGroupList().remove(w);
                    removetagFromGroupList(tmpUser.get(0), groupTag);
                    w.getNoteList().add(note);
                    tmpUser.get(0).addToGroupList(w);

                }
                } );

        userRepo.save(tmpUser.get(0));
        tmpUser.remove(0);
        return noteRepo.findByBaseUserModel_Name(note.getBaseUserModel().getName());
    }

    private User removetagFromAllLists(User user , String tagName)
    {
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


    private NoteDto convertEntityToDto(Note note) {
        noteDto = modelMapper.map(note, NoteDto.class);
        return noteDto;
    }

    private Note convertDtoToEntity(NoteDto noteDto) {
        note = modelMapper.map(noteDto, Note.class);
        return note;
    }
}
