package cafe.managment.service;

import cafe.managment.dto.CategoryDto;
import cafe.managment.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(CategoryDto categoryDto);
    List<Category> findAll();
    List<Category> findAcivatedCategory();
    Category findById(Long id);
    Category updateCategory(Long id,CategoryDto categoryDto);
    Category delete(Long id);
}
