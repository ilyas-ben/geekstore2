package com.ilouse.geekstoreV2.Service.Cart;

import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Repository.CartRepo;
import com.ilouse.geekstoreV2.Service.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        Optional<Cart> cartOptional = cartRepo.findById(id);
        return cartOptional.orElse(null);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        if (cartRepo.existsById(id)) {
            cart.setId(id);
            return cartRepo.save(cart);
        }
        return null;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepo.deleteById(id);
    }
}
