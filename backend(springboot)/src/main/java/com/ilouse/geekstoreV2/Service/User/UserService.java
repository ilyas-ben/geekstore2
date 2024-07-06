package com.ilouse.geekstoreV2.Service.User;

import com.ilouse.geekstoreV2.Model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

    User getByUsername(String username);
}
