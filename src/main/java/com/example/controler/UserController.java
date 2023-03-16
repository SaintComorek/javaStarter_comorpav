package com.example.controler;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.UserRepo;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/api/users")
    public List<User> postUsers(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return userService.getListOfUsers();
    }

    ///api/users/{id} (PUT, GET, DELETE)

    @GetMapping("/api/users/{id}")
    public Optional<User> getUsers(@PathVariable long id) {
        return userRepo.findById(id);
    }

    @PutMapping("api/users/{id}")
    public List<User> putUsers(@RequestBody UserDto userDto, @PathVariable Long id)
    {
        return userService.putMethod(userDto , id);
    }

    @DeleteMapping ("api/users/{id}")
    public List<User> deleteUsers(@PathVariable Long id)
    {
        return userService.deleteMethod(id);
    }

}

