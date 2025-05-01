package com.ilouse.geekstoreV2.Repository;

import com.ilouse.geekstoreV2.Model.Category;
import com.ilouse.geekstoreV2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    // Additional custom queries can be added here if needed
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);

}
