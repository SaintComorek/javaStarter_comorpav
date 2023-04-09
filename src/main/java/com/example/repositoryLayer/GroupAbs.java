package com.example.repositoryLayer;

import com.example.model.Group;
import com.example.repository.GroupRepo;

import java.util.List;

public abstract class GroupAbs implements GroupRepo {

   public abstract List<Group> findGroupById(long id);
   public abstract List<Group> findGroupByBaseUserModel_Name(String name);
   public abstract List<Group> findGroupByBaseUserModel_LastName(String lastName);


}
