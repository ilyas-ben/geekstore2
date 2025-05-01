package com.ilouse.geekstoreV2.Repository;

import com.ilouse.geekstoreV2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
