package com.ilouse.geekstoreV2.Service.Cart;

import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Model.Product;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();
    Cart getCartById(Long id);
    Cart saveCart(Cart cart);
    void deleteCart(Long id);
    Cart updateCart(Cart cart);


    Cart getCartByUserId(Long userId);
    void emptyCartByUserId(Long userId);
    void setProductQuantityInCartByUserId(Long productId, Integer quantity, Long userId);
}
