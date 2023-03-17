package com.example.repository;

import com.example.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface GroupRepo extends JpaRepository<Group , Long> {
}
