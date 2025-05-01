package com.ilouse.geekstoreV2.Service.Cart;

import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Model.CartItem;
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
    Boolean addProductToCart(Long productId, Long userId);
    boolean isProductInCart(Long productId, Cart cart);
    void incrementProductQuantityInCartByUserIdAndProductId(Long productId, Long userId);

}
