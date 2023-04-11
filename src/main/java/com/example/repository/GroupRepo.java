package com.example.repository;

import com.example.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group , Long> {
    List<Group> findGroupByBaseUserModel_Name(String name);
    List<Group> findGroupByBaseUserModel_Username(String username);
    Group findByBaseUserModel_UsernameAndName(String username , String groupname);
    Group findById(long id);
    //Group findByBaseUserModel_NameAndBaseUserModel_LastNameAndName( String name , String lastname ,String groupname );



   // List<Group> findByTag_TagName(String tagName);

    //List<Group> findGroupByBaseUserModel_NameAndTag_TagName(String name , String tagName);

}
