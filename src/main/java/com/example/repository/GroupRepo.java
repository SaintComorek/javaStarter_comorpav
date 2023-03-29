package com.example.repository;

import com.example.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group , Long> {
  //  List<Group> findGroupByBaseUserModel_EmailAddress(String emailAddress);
    List<Group> findGroupByBaseUserModel_Name(String name);
    List<Group> findGroupByBaseUserModel_LastName(String lastName);
    List<Group> findByTag_TagName(String tagName);

}
