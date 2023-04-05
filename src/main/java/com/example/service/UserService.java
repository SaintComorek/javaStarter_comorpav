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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    private DtoCollection dtoCollection = new DtoCollection();
    private UserDto userDto;
    private User user;
    private ModelCollection modelCollection = new ModelCollection();
    private Note note;

    public List<UserDto> getAllUsers() {

        return this.userRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public List<User> findByName(String name) {
        return userRepo.findUserByName(name);
    }

    public List<User> findByLastname(String lastName) {
        return userRepo.findUserByLastName(lastName);
    }

    public List<User> putMethod(UserDto userDto, long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            deleteMethod(id);
            user = convertDtoToEntity(userDto);
            userRepo.save(user);
        }
        return userRepo.findAll();
    }

    public List<User> deleteMethod(long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            userRepo.delete(user);
        }
        return userRepo.findAll();
    }
    public void deleteUser(User user)
    {
        userRepo.delete(user);
    }
    public void saveUser(User user)
    {
        userRepo.save(user);
    }



    public List<User> deleteUserByLastName(String lastName) {
        List<User> tmpUser = userRepo.findAll();
        tmpUser.stream()
                .forEach(w -> {
                    if (w.getLastName().equals(lastName)) {
                        userRepo.delete(w);
                    }
                });
        return userRepo.findAll();
    }

    public List<User> addUser(UserDto userDto) {

        user = new User();
        user = convertDtoToEntity(userDto);
        dtoCollection.addUserDto(userDto);
        modelCollection.addToUser(user);
        userRepo.save(user);
        return modelCollection.getUserList();
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

    public List<UserDto> getListOfUsersDto() {
        if (!dtoCollection.getUserDtoList().isEmpty()) {
            return dtoCollection.getUserDtoList();
        }
        return Collections.emptyList();
    }

    public List<User> getListOfUsers() {
        if (!modelCollection.getUserList().isEmpty()) {
            return modelCollection.getUserList();
        }
        return Collections.emptyList();
    }
}
