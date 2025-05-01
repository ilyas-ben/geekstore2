package com.ilouse.geekstoreV2.Controller;


import com.ilouse.geekstoreV2.Configuration.jwt.JwtUtils;
import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Service.Cart.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public CartService cartService;


    @GetMapping("/byuserid") // checked
    public ResponseEntity<Cart> getCartByUserId(HttpServletRequest request) {
        String token = jwtUtils.getJwtFromHeader(request);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @DeleteMapping("/byuserid") // checked
    public ResponseEntity<Void> emptyCartByUserId(HttpServletRequest request) {
        String token = jwtUtils.getJwtFromHeader(request);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        cartService.emptyCartByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/setProductQuantity") //checked
    public ResponseEntity<Void> setProductQuantityInCartByCartId(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        Long productId = ((Number) body.get("productId")).longValue();
        Integer quantity = (Integer) body.get("quantity");

        String token = jwtUtils.getJwtFromHeader(request);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
       // Long cartId = ((Number) body.get("cartId")).longValue();

        cartService.setProductQuantityInCartByUserId(productId, quantity, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Boolean> addProductToCart(@RequestBody Long productId, HttpServletRequest request){
        String token = jwtUtils.getJwtFromHeader(request);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);

        return ResponseEntity.ok(cartService.addProductToCart(productId,userId));
    }

}
