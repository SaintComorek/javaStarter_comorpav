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
    public List<Note> findNote(String name)
    {
        return noteRepo.findByTag_TagName(name);
    }
    public List<Note> findNoteByUserName(String name)
    {
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
    public List<Note> addNote(NoteDto noteDto) {
        note = convertDtoToEntity(noteDto);
        noteRepo.save(note);
        return noteRepo.findAll();
    }

 */
    public List<Note> addNote(NoteDto noteDto) {
        note = convertDtoToEntity(noteDto);
        List<User> tmp = userRepo.findUserByName(note.getBaseUserModel().getName());
        user = tmp.get(0);
        userRepo.delete(tmp.get(0));
        tmp.remove(0);
        user.addToNoteList(note);
        userRepo.save(user);
        return noteRepo.findAll();

    }


    public List<Note> addNoteToGroup(NoteDto noteDto , String groupTag)
    {
        note = convertDtoToEntity(noteDto);

        List<User> tmp = userRepo.findUserByName(note.getBaseUserModel().getName());
        user = tmp.get(0);
        userRepo.delete(tmp.get(0));
        tmp.remove(0);

        List<Group> tmpGroup = groupRepo.findGroupByBaseUserModel_Name("el");
        List<Group> tmpGroup2 = groupRepo.findGroupByBaseUserModel_Name(note.getBaseUserModel().getName());



        group = tmpGroup.get(0);
        groupRepo.delete(tmpGroup.get(0));
        tmpGroup.remove(0);
        group.addGroup_noteList(note);
        user.addToGroupList(group);
        userRepo.save(user);
        // groupRepo.delete(group);
        //groupRepo.save(group);

        //user = tmp.get(0);
        //tmpGroup.get(0).
        //user.addToNoteList(note);
        //userRepo.delete(tmp.get(0));
        //tmp.remove(0);
        //tmpGroup.get(0).setNote(note);
        //  userRepo.save(user);

        return noteRepo.findAll();
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
