package com.ilouse.geekstoreV2.Service.User;

import com.ilouse.geekstoreV2.Model.User;
import com.ilouse.geekstoreV2.Repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_returnsList() {
        List<User> users = List.of(new User(), new User());
        when(userRepo.findAll()).thenReturn(users);
        assertEquals(users, userService.getAllUsers());
    }

    @Test
    void getUserById_found() {
        User user = new User();
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserById(1L));
    }

    @Test
    void getUserById_notFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
        assertNull(userService.getUserById(1L));
    }

    @Test
    void createUser_savesUser() {
        User user = new User();
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(user, userService.createUser(user));
    }

    @Test
    void updateUser_existing() {
        User user = new User();
        user.setUsername("new");
        user.setPassword("pwd");
        user.setEmail("mail");

        User dbUser = new User();
        when(userRepo.findById(1L)).thenReturn(Optional.of(dbUser));
        when(userRepo.save(any(User.class))).thenReturn(dbUser);

        assertNotNull(userService.updateUser(1L, user));
    }

    @Test
    void updateUser_notExisting() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
        assertNull(userService.updateUser(1L, new User()));
    }

    @Test
    void deleteUser_callsDeleteById() {
        doNothing().when(userRepo).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepo).deleteById(1L);
    }

    @Test
    void loadUserByUsername_found() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("1234");
        user.setRoles("USER");

        when(userRepo.findByUsername("test")).thenReturn(user);

        assertNotNull(userService.loadUserByUsername("test"));
    }

    @Test
    void loadUserByUsername_notFound() {
        when(userRepo.findByUsername("test")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("test"));
    }
}
