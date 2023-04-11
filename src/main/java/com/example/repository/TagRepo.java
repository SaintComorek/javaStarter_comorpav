package com.example.repository;

import com.example.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

    List<Tag> findByBaseUserModel_Username(String username);
    Tag findById(long id );
    Tag findTagByTagNameAndBaseUserModel_Username(String tagname, String username);

}
