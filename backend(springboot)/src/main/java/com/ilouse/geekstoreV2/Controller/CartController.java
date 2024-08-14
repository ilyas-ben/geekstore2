package com.ilouse.geekstoreV2.Controller;


import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Service.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    public CartService cartService;




    @GetMapping("/byuserid/{userId}") // checked
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
         return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @DeleteMapping("/byuserid/{userId}")
    public ResponseEntity<Void> emptyCartByUserId(@PathVariable Long userId) {
        cartService.emptyCartByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/setProductQuantity/{userId}")
    public ResponseEntity<Void> setProductQuantityInCartByCartId(@RequestBody Map<String, Object> body, @PathVariable  Long userId) {
        Long productId = ((Number) body.get("productId")).longValue();
        Integer quantity = (Integer) body.get("quantity");
       // Long cartId = ((Number) body.get("cartId")).longValue();

        cartService.setProductQuantityInCartByUserId(productId, quantity, userId);
        return ResponseEntity.ok().build();
    }


}
