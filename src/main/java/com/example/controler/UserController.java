package com.example.controler;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping()
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/find/user/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.findById(id);
    }
    @GetMapping("/find/user")
    public User getUserByUsername(@RequestParam String username)
    {
        return userService.findUser(username);
    }
    @GetMapping("/find/all/name")
    public List<User> getUsersByName(@RequestParam String name)
    {
        return userService.findByName(name);
    }
    @GetMapping("/find/all/lastname")
    public List<User> getUsersByLastName(@RequestParam String lastname)
    {
        return userService.findByLastname(lastname);
    }
    @PostMapping("create")
    public User createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return userService.findUser(userDto.getUsername());
    }
    @PutMapping("/update/{id}")
    public User updateUserById(@RequestBody UserDto userDto, @PathVariable Long id)  {
        return userService.updateUser(id, userDto);
    }

    @PutMapping("/update/user")
    public User updateUserByUserName(@RequestBody UserDto userDto, @RequestParam String username) {
        return userService.updateUser(username, userDto);
    }
    @PutMapping("/update/username/user")
    public User updateUserUserName(@RequestBody UserDto userDto, @RequestParam String username) {
        return userService.updateUserName(username, userDto);
    }
    @PutMapping("/update/lastname/user")
    public User updateUserLastName(@RequestBody UserDto userDto, @RequestParam String username) {
        return userService.updateUserLastName(username, userDto);
    }
    @PutMapping("/update/email/user")
    public User updateUserEmail(@RequestBody UserDto userDto, @RequestParam String username) {
        return userService.updateEmail(username, userDto);
    }
    @PutMapping("/update/pass/user")
    public User updateUserPass(@RequestBody UserDto userDto, @RequestParam String username) {
        return userService.updatePassword(username, userDto);
    }
    @PutMapping("/update/status/user")
    public User updateUserStatus(@RequestBody UserDto userDto, @RequestParam String username) {
        return userService.updateUserStatus(username, userDto);
    }
    @DeleteMapping("/delete/user/{id}")
    public void deleteUsers(@PathVariable Long id) {
        userService.deleteMethod(id);
    }

    @DeleteMapping("/delete/user")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @DeleteMapping("/delete/lastname/user")
    public List<User> deleteUsers(@RequestParam String lastname) {
        return userService.deleteUserByLastName(lastname);
    }
}

