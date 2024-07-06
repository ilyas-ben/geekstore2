package com.ilouse.geekstoreV2.Service.Cart;

import com.ilouse.geekstoreV2.Model.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();

    Cart getCartById(Long id);

    Cart createCart(Cart cart);

    Cart updateCart(Long id, Cart cart);

    void deleteCart(Long id);
}
