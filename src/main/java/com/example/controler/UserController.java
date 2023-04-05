package com.example.controler;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.UserRepo;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;


    @GetMapping()
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping()
    public List<User> postUsers(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return userService.getListOfUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUsers(@PathVariable long id) {
        return userRepo.findById(id);
    }

    @PutMapping("{id}")
    public List<User> putUsers(@RequestBody UserDto userDto, @PathVariable Long id)
    {
        return userService.putMethod(userDto , id);
    }

    @DeleteMapping ("/delete/id/{id}")
    public List<User> deleteUsers(@PathVariable Long id)
    {
        return userService.deleteMethod(id);
    }

    @DeleteMapping ("/delete/lastname/{lastname}")
    public List<User> deleteUsers(@PathVariable String lastname)
    {
        return userService.deleteUserByLastName(lastname);
    }
}

