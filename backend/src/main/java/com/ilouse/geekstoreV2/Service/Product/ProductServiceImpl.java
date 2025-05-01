package com.ilouse.geekstoreV2.Service.Product;

import com.ilouse.geekstoreV2.Model.Category;
import com.ilouse.geekstoreV2.Model.Product;
import com.ilouse.geekstoreV2.Repository.ProductRepo;
import com.ilouse.geekstoreV2.Service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryService categoryService;



    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        Long categoryId = product.getCategory().getId();
        Category category =  categoryService.getCategoryById(categoryId);
        product.setCategory(category);
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (productRepo.existsById(id)) {
            product.setId(id); // Ensure the product id is set for update
            return productRepo.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepo.getProductsByCategoryId(categoryId);
    }
}
