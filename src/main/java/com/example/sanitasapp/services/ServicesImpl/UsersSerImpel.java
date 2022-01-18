package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Users;
import com.example.sanitasapp.repository.UsersRepository;
import com.example.sanitasapp.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersSerImpel implements UsersServices {
    private final UsersRepository userRepository;

    @Autowired
    public UsersSerImpel(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean addUser(Users user){
        Optional<Users> usersOptional = userRepository.findByUsername(user.getUsername());
        if(usersOptional.isPresent()){
            return true;
        }
        userRepository.save(user);
        return false;
    }

    public String validateUser(Users user) {
        if(user.getUsername().equals("admin") && user.getPassword().equals("admin")){
            return "Admin";
        }

        if(user.getUsername().equals("admin") && !user.getPassword().equals("admin")){
            return "Incorrect Password";
        }

        Optional<Users> user1 = userRepository.findByUsername(user.getUsername());
        if (user1.isEmpty()) {
            return "Not Registered Email";
        }

        if (!user1.get().getPassword().equals(user.getPassword())) {
            return "Incorrect Password";
        }

        System.out.println("done");
        return "approved";
    }

    public Optional<Users> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }
}
