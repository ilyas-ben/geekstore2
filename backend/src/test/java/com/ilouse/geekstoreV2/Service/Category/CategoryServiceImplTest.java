package com.ilouse.geekstoreV2.Service.Category;

import com.ilouse.geekstoreV2.Model.Category;
import com.ilouse.geekstoreV2.Repository.CategoryRepo;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_returnsList() {
        List<Category> categories = List.of(new Category());
        when(categoryRepo.findAll()).thenReturn(categories);
        assertEquals(categories, categoryService.getAllCategories());
    }

    @Test
    void getCategoryById_found() {
        Category category = new Category();
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
        assertEquals(category, categoryService.getCategoryById(1L));
    }

    @Test
    void getCategoryById_notFound() {
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());
        assertNull(categoryService.getCategoryById(1L));
    }

    @Test
    void createCategory_success() {
        Category category = new Category();
        when(categoryRepo.save(category)).thenReturn(category);
        assertEquals(category, categoryService.createCategory(category));
    }

    @Test
    void updateCategory_found() {
        Category input = new Category();
        input.setName("New");

        Category existing = new Category();
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(categoryRepo.save(existing)).thenReturn(existing);

        assertEquals(existing, categoryService.updateCategory(1L, input));
    }

    @Test
    void updateCategory_notFound() {
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());
        assertNull(categoryService.updateCategory(1L, new Category()));
    }

    @Test
    void deleteCategory_success() {
        doNothing().when(categoryRepo).deleteById(1L);
        categoryService.deleteCategory(1L);
        verify(categoryRepo).deleteById(1L);
    }
}
