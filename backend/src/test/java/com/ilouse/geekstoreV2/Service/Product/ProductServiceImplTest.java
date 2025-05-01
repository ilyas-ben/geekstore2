package com.ilouse.geekstoreV2.Service.Product;

import com.ilouse.geekstoreV2.Model.*;
import com.ilouse.geekstoreV2.Repository.ProductRepo;
import com.ilouse.geekstoreV2.Service.Category.CategoryService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        List<Product> products = List.of(new Product(), new Product());
        when(productRepo.findAll()).thenReturn(products);
        assertEquals(products, productService.getAllProducts());
    }

    @Test
    void getProductById_found() {
        Product product = new Product();
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        assertEquals(product, productService.getProductById(1L));
    }

    @Test
    void getProductById_notFound() {
        when(productRepo.findById(1L)).thenReturn(Optional.empty());
        assertNull(productService.getProductById(1L));
    }

    @Test
    void createProduct_success() {
        Product product = new Product();
        Category category = new Category();
        category.setId(1L);
        product.setCategory(category);

        when(categoryService.getCategoryById(1L)).thenReturn(category);
        when(productRepo.save(product)).thenReturn(product);

        assertEquals(product, productService.createProduct(product));
    }

    @Test
    void updateProduct_exists() {
        Product product = new Product();
        when(productRepo.existsById(1L)).thenReturn(true);
        when(productRepo.save(product)).thenReturn(product);

        assertEquals(product, productService.updateProduct(1L, product));
    }

    @Test
    void updateProduct_notExists() {
        when(productRepo.existsById(1L)).thenReturn(false);
        assertNull(productService.updateProduct(1L, new Product()));
    }

    @Test
    void deleteProduct_success() {
        doNothing().when(productRepo).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepo).deleteById(1L);
    }

    @Test
    void getProductsByCategoryId_returnsList() {
        List<Product> products = List.of(new Product());
        when(productRepo.getProductsByCategoryId(1L)).thenReturn(products);
        assertEquals(products, productService.getProductsByCategoryId(1L));
    }
}
