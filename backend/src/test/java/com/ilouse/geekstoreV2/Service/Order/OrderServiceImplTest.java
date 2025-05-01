package com.ilouse.geekstoreV2.Service.Order;

import com.ilouse.geekstoreV2.Model.Order;
import com.ilouse.geekstoreV2.Repository.OrderRepo;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepo orderRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders_returnsList() {
        List<Order> orders = List.of(new Order());
        when(orderRepo.findAll()).thenReturn(orders);
        assertEquals(orders, orderService.getAllOrders());
    }

    @Test
    void getOrderById_found() {
        Order order = new Order();
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        assertEquals(order, orderService.getOrderById(1L));
    }

    @Test
    void getOrderById_notFound() {
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());
        assertNull(orderService.getOrderById(1L));
    }

    @Test
    void createOrder_success() {
        Order order = new Order();
        when(orderRepo.save(order)).thenReturn(order);
        assertEquals(order, orderService.createOrder(order));
    }

    @Test
    void updateOrder_exists() {
        Order order = new Order();
        when(orderRepo.existsById(1L)).thenReturn(true);
        when(orderRepo.save(order)).thenReturn(order);
        assertEquals(order, orderService.updateOrder(1L, order));
    }

    @Test
    void updateOrder_notExists() {
        when(orderRepo.existsById(1L)).thenReturn(false);
        assertNull(orderService.updateOrder(1L, new Order()));
    }

    @Test
    void deleteOrder_success() {
        doNothing().when(orderRepo).deleteById(1L);
        orderService.deleteOrder(1L);
        verify(orderRepo).deleteById(1L);
    }
}
