package com.ilouse.geekstoreV2.Service.Order;

import com.ilouse.geekstoreV2.Model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order order);

    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id);
}
