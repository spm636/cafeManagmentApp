package cafe.managment.controller;

import cafe.managment.dto.CategoryDto;
import cafe.managment.model.Category;
import cafe.managment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/saveCategory")
    public Category saveCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.save(categoryDto);
   }

//@PostMapping("/saveCategory")
//public ResponseEntity<Category> saveCategory(@RequestBody CategoryDto categoryDto){
//Category category=categoryService.save(categoryDto);
//return new ResponseEntity<>(category, HttpStatus.OK);
//}


    @GetMapping("/showCategory")
    public List<Category> showActivatedCategory(){
        return categoryService.findAcivatedCategory();
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody CategoryDto categoryDto){
        Category category=categoryService.updateCategory(id,categoryDto);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Category>  findCategoryById(@PathVariable Long id){
        Category category= categoryService.findById(id);
        if(category==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }
    @GetMapping("/deleteCategory/{id}")
    public Category deleteCategory(@PathVariable Long id){
       return categoryService.delete(id);
    }
}
