package com.example.repository;

import com.example.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    Note findByBaseUserModel_UsernameAndName(String username , String name);

    List<Note> findByBaseUserModel_Username(String username);
    List<Note> findById(long id);
    List<Note> findByBaseUserModel_LastName(String lastName);

    List<Note> findByBaseUserModel_Name(String userName);
    List<Note> findByBaseUserModel_NameAndBaseUserModel_LastName(String name , String lastname);

}
