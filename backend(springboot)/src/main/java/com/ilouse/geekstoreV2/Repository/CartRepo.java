package com.ilouse.geekstoreV2.Repository;

import com.ilouse.geekstoreV2.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    // Additional custom queries can be added here if needed
    @Modifying
    @Transactional //
    @Query("delete from CartItem ci where ci.cart.client.id = :userId")
    void emptyCartByUserId(@Param("userId") Long userId);

    @Query("from Cart c where c.client.id = :userId")
    Cart getCartByUserId(@Param("userId") Long userId);
}
