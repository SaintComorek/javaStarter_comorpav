package com.example.repository;

import com.example.dto.BaseUserDto;
import com.example.model.BaseUserModel;
import com.example.model.Tag;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    List<Tag> findByLastname(String lastname);



}
