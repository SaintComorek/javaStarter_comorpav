package com.example.repository;

import com.example.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
   // List<Note> findByBaseUserModel_EmailAddress(String emaillAddress);
    List<Note> findByBaseUserModel_LastName(String lastName);
    List<Note> findByBaseUserModel_Name(String userName);
    //List<Note> findByTag_TagName(String tagName);

}
