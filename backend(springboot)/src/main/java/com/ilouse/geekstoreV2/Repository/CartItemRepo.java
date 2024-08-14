package com.ilouse.geekstoreV2.Repository;




import com.ilouse.geekstoreV2.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.id = :id")
    void deleteCartItemById(@Param("id") Long id);

}

