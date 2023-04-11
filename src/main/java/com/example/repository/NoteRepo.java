package com.example.repository;

import com.example.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    List<Note> findByBaseUserModel_Username(String username);


    List<Note> findByTags(String tag);

    Note findByNameAndBaseUserModel_Username(String notename , String username);

    List<Note> findById(long id);

}
