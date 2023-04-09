package com.example.service;

import com.example.dto.DtoCollection;
import com.example.dto.UserDto;
import com.example.model.ModelCollection;
import com.example.model.Note;
import com.example.model.User;
import com.example.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    private UserDto userDto;
    private User user;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUser(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findByName(String name) {
        return userRepo.findUserByName(name);
    }

    public List<User> findByLastname(String lastName) {
        return userRepo.findUserByLastName(lastName);
    }

    public List<User> getUser(String name, String lastname) {
        return userRepo.findUserByNameAndLastName(name, lastname);
    }

    public List<User> getUserAsList(String name, String lastname) {
        return userRepo.findUserByNameAndLastName(name, lastname);
    }


    public User findById(long id) {

        return userRepo.findById(id);
    }

    public User updateUser(long id, UserDto userDto) {
        user = findById(id);
        deleteUser(user);
        user = convertDtoToEntity(userDto);
        saveUser(user);
        return user;
    }

    public User updateUser(String username, UserDto userDto) {
        try {
            user = findUser(username);
            deleteUser(user);
            user = convertDtoToEntity(userDto);
            saveUser(user);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

        return user;
    }

    public User updateUserStatus(String username, UserDto userDto) {
        user = findUser(username);
        deleteUser(user);
        user.setStatus(userDto.getStatus());
        saveUser(user);
        return user;
    }

    public User updateUserName(String username, UserDto userDto) {
        user = findUser(username);
        deleteUser(user);
        user.setUsername(userDto.getUsername());
        saveUser(user);
        return user;
    }

    public User updateUserLastName(String username, UserDto userDto) {
        user = findUser(username);
        deleteUser(user);
        user.setLastName(userDto.getLastName());
        saveUser(user);
        return user;
    }

    public User updatePassword(String username, UserDto userDto) {
        user = findUser(username);
        deleteUser(user);
        user.setPassword(userDto.getPassword());
        saveUser(user);
        return user;
    }

    public User updateEmail(String username, UserDto userDto) {
        user = findUser(username);
        deleteUser(user);
        user.setPassword(userDto.getEmailAddress());
        saveUser(user);
        return user;
    }

    public void deleteMethod(long id) {
        deleteUser(findById(id));
    }

    public void deleteUser(String username) {
        try {
            deleteUser(findUser(username));
        } catch (Exception e) {
            System.out.println("No user with username: " + username + " found");
        }
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public void deleteAllUsers(List<User> list) {
        userRepo.deleteAll(list);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }


    public List<User> deleteUserByLastName(String lastName) {
        List<User> tmpUser = userRepo.findUserByLastName(lastName);
        deleteAllUsers(tmpUser);
        tmpUser.clear();
        return findByLastname(lastName);
    }

    public User createUser(UserDto userDto) {
        if (userDto.getUsername() == null)
            userDto.setUsername(autoUserName(userDto.getName(), userDto.getLastName()));
        user = convertDtoToEntity(userDto);
        userRepo.save(user);
        return findUser(user.getUsername());
    }

    private String autoUserName(String name, String lastname) {
        try {
            lastname = lastname.substring(0, 5);
            lastname += name.substring(0, 3);
        } catch (Exception e) {
            lastname = lastname.substring(0, lastname.length() - 1);
            lastname += name.substring(0, name.length() - 1);
        }

        return lastname;
    }


    public UserDto convertEntityToDto(User user) {
        modelMapper = new ModelMapper();
        userDto = new UserDto();
        userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User convertDtoToEntity(UserDto userDto) {
        modelMapper = new ModelMapper();
        user = new User();
        user = modelMapper.map(userDto, User.class);
        return user;
    }
}
