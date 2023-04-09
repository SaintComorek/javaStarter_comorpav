package com.example.service;

import com.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    @Autowired
    UserRepo userRepo;




}
