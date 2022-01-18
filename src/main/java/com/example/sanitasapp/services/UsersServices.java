package com.example.sanitasapp.services;

import com.example.sanitasapp.models.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UsersServices {
    List<Users> getAllUsers();

    boolean addUser(Users user);

    String validateUser(Users user);

    Optional<Users> getUserByUsername(String username);

    void deleteUserByUsername(String username);
}
