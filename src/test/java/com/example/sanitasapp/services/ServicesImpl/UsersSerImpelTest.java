package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Users;
import com.example.sanitasapp.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersSerImpelTest {

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UsersSerImpel usersServices;

    private Users users;
    private List<Users> usersList;

    @BeforeEach
    void setUp() {
        users = Users.builder()
                .userId(1L)
                .username("james@patient.com")
                .password("henry124")
                .build();

        Users users1 = Users.builder()
                .userId(2L)
                .username("kane@patient.com")
                .password("henry124")
                .build();

        usersList = List.of(users, users1);
    }

    @Test
    void getAllUsers() {

        when(userRepository.findAll()).thenReturn(usersList);

        usersServices.getAllUsers();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void addUser() {
        // mock UserRepository
        when(userRepository.save(any(Users.class))).thenReturn(users);

        //Call the method to be tested
        usersServices.addUser(users);

        //Make Assertion
        verify(userRepository, times(1)).save(any(Users.class));
    }

//    @Test
//    void validateUser() {
//    }

    @Test
    void getUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(users));

        Optional<Users> foundUsers = usersServices.getUserByUsername("user@email.com");

        assertNotNull(foundUsers);
        assertEquals("james@patient.com", foundUsers.get().getUsername());

        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void deleteUserByUsername() {
        // mock UserRepository
        when(userRepository.deleteByUsername(anyString())).thenReturn(1);

        //Call the method to be tested
        usersServices.deleteUserByUsername(users.getUsername());

        //Make Assertion
        verify(userRepository, times(1)).deleteByUsername(anyString());
    }
}