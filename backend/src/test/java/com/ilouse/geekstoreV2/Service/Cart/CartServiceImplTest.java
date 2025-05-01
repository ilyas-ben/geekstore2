package com.ilouse.geekstoreV2.Service.Cart;

import com.ilouse.geekstoreV2.Model.Cart;
import com.ilouse.geekstoreV2.Model.CartItem;
import com.ilouse.geekstoreV2.Model.Product;
import com.ilouse.geekstoreV2.Model.User;
import com.ilouse.geekstoreV2.Repository.CartItemRepo;
import com.ilouse.geekstoreV2.Repository.CartRepo;
import com.ilouse.geekstoreV2.Service.Product.ProductService;
import com.ilouse.geekstoreV2.Service.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepo cartRepo;

    @Mock
    private CartItemRepo cartItemRepo;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCartByUserId() {
        Long userId = 1L;
        Cart cart = new Cart();
        when(cartRepo.getCartByUserId(userId)).thenReturn(cart);

        Cart result = cartService.getCartByUserId(userId);

        assertEquals(cart, result);
        verify(cartRepo).getCartByUserId(userId);
    }

    @Test
    void testAddProductToCart_NewProduct() {
        Long userId = 1L;
        Long productId = 2L;
        User user = new User();
        Product product = new Product();
        Cart cart = new Cart();
        cart.setClient(user);
        cart.setCartItems(new ArrayList<>());

        when(cartRepo.getCartByUserId(userId)).thenReturn(cart);
        when(userService.getUserById(userId)).thenReturn(user);
        when(productService.getProductById(productId)).thenReturn(product);
        when(cartRepo.save(any(Cart.class))).thenReturn(cart);

        boolean added = cartService.addProductToCart(productId, userId);

        assertTrue(added);
        verify(cartRepo).save(any(Cart.class));
    }

    @Test
    void testIsProductInCart_True() {
        Long productId = 2L;
        Product product = new Product();
        product.setId(productId);

        CartItem item = new CartItem();
        item.setProduct(product);

        Cart cart = new Cart();
        cart.setCartItems(List.of(item));

        assertTrue(cartService.isProductInCart(productId, cart));
    }

    @Test
    void testIsProductInCart_False() {
        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        assertFalse(cartService.isProductInCart(1L, cart));
    }

    @Test
    void testEmptyCartByUserId() {
        Long userId = 1L;
        doNothing().when(cartRepo).emptyCartByUserId(userId);

        cartService.emptyCartByUserId(userId);

        verify(cartRepo).emptyCartByUserId(userId);
    }

    @Test
    void testGetCartById_WhenPresent() {
        Long cartId = 5L;
        Cart cart = new Cart();
        when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartById(cartId);

        assertEquals(cart, result);
    }

    @Test
    void testGetCartById_WhenAbsent() {
        Long cartId = 5L;
        when(cartRepo.findById(cartId)).thenReturn(Optional.empty());

        Cart result = cartService.getCartById(cartId);

        assertNull(result);
    }
}
