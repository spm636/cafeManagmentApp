package cafe.managment.serviceImpl;

import cafe.managment.dto.CategoryDto;
import cafe.managment.model.Category;
import cafe.managment.repository.CategoryRepository;
import cafe.managment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category save(CategoryDto categoryDto) {
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setActivted(true);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAcivatedCategory() {
        return categoryRepository.findActivatedCategory();
    }

    @Override
    public Category findById(Long id) {

        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category category=categoryRepository.getReferenceById(id);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(Long id) {
        Category category=categoryRepository.getReferenceById(id);
        category.setActivted(false);
        return categoryRepository.save(category);
    }
}
