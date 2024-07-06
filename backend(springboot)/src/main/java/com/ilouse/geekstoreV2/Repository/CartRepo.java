package com.ilouse.geekstoreV2.Repository;

import com.ilouse.geekstoreV2.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    // Additional custom queries can be added here if needed
}
