package com.example.repository;

import com.example.model.Tag;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findUserByName(String name);
    List<User> findUserByLastName(String lastName);
}
