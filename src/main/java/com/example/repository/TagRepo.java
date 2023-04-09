package com.example.repository;

import com.example.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

    Tag findByBaseUserModel_Username(String username);
    List<Tag> findTagByTagName(String tagName);
    List<Tag> findTagByBaseUserModel_Name(String name);
    List<Tag> findTagByBaseUserModel_LastName(String lastName);
   // List<Tag> findTagByBaseUserModel_EmailAddress(String emailAddress);
}
