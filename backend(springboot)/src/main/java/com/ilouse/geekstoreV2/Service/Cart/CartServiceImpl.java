package com.ilouse.geekstoreV2.Service.Cart;

import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Model.CartItem;
import com.ilouse.geekstoreV2.Model.Product;
import com.ilouse.geekstoreV2.Model.User;
import com.ilouse.geekstoreV2.Repository.CartItemRepo;
import com.ilouse.geekstoreV2.Repository.CartRepo;
import com.ilouse.geekstoreV2.Repository.UserRepo;
import com.ilouse.geekstoreV2.Service.Cart.CartService;
import com.ilouse.geekstoreV2.Service.Product.ProductService;
import com.ilouse.geekstoreV2.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemRepo cartItemRepo;


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
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepo.save(cart);
    }



    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepo.getCartByUserId(userId);
    }
    @Override
    public void emptyCartByUserId(Long userId) {
        cartRepo.emptyCartByUserId(userId);
    }

    @Override
    public void setProductQuantityInCartByUserId(Long productId, Integer quantity, Long userId)  {
        User user = userService.getUserById(userId);
        Cart cart = getCartByUserId(userId);
        boolean productExists = false;

        if(cart==null) {
            cart = new Cart();
            cart.setClient(user);
        }

        else {
            if (cart.getCartItems() != null) {
                for (CartItem cartItem : cart.getCartItems()) {
                    if (cartItem.getProduct().getId() == productId) {
                        cartItem.setQuantity(quantity);
                        if (quantity == 0) {
                            System.out.println("it should be deleted");
                            cart.getCartItems().remove(cartItem);
                        }
                        productExists = true;
                        break;
                    }
                }
            }
        }

        if(!productExists) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productService.getProductById(productId));
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            List<CartItem> cartItems = (cart.getCartItems() == null) ? new ArrayList<CartItem>() : cart.getCartItems();
            System.out.println(cartItems);
            cartItems.add(cartItem);
            System.out.println(cart);
            cart.setCartItems(cartItems);
        }

        saveCart(cart);
    }




}
