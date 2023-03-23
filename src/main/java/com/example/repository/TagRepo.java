package com.example.repository;

import com.example.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    List<Tag> findTagByTagName(String name);
    List<Tag> findTagByBaseUserModel_Name(String name);
    List<Tag> findTagByBaseUserModel_LastName(String lastName);


    //List<BaseUserModel> fi

}
