package com.ilouse.geekstoreV2.Service.User;

import com.ilouse.geekstoreV2.Model.User;

import com.ilouse.geekstoreV2.Repository.UserRepo;
import com.ilouse.geekstoreV2.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepo userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve the username
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            return user;
        }
        return  null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //  return
        User user =userRepository.findByUsername(username);
        System.out.println(username);
        if(user==null) throw new UsernameNotFoundException(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(splitRolesInArray(user.getRoles()))
                .build();

    }

    private String[] splitRolesInArray(String roles) {
        return (roles != null) ? roles.split(",") : new String[]{"0"};
    }



    //
}
