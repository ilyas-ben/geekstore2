package com.ilouse.geekstoreV2.Service.Order;

import com.ilouse.geekstoreV2.Model.Order;
import com.ilouse.geekstoreV2.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        if (orderRepo.existsById(id)) {
            order.setId(id); // Ensure the order ID matches the request ID
            return orderRepo.save(order);
        }
        return null; // Or handle as needed if order ID doesn't exist
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
