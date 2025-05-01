package com.ilouse.geekstoreV2.Repository;

import com.ilouse.geekstoreV2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
