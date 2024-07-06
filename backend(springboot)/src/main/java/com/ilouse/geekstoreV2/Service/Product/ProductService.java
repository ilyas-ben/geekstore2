package com.ilouse.geekstoreV2.Service.Product;

import com.ilouse.geekstoreV2.Model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
    List<Product> getProductsByCategoryId(Long categoryId);
}
