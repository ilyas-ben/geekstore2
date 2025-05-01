package com.ilouse.geekstoreV2.Repository;


import com.ilouse.geekstoreV2.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
