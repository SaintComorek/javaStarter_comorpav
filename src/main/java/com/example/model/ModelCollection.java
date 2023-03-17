package com.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelCollection {
    private List<User> userList = new ArrayList<>();
    private List<Tag> tagList = new ArrayList<>();
    private List<Note> noteList = new ArrayList<>();
    private List<Group> groupList = new ArrayList<>();

    public void  addToUser(User user)
    {
        userList.add(user);
    }
    public void  addToNote(Note note)
    {
        noteList.add(note);
    }
    public void  addToTag(Tag tag)
    {
        tagList.add(tag);
    }
    public void  addToGroup(Group group)
    {
        groupList.add(group);
    }

}
