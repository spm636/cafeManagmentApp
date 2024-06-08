package cafe.managment.controller;

import cafe.managment.dto.ProductDto;
import cafe.managment.model.Product;
import cafe.managment.service.ProductService;
import jakarta.annotation.Resource;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ProductController {



    @Autowired
    ProductService productService;
    //@RequestParam("imagesUrl") MultipartFile multipartFile

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@ModelAttribute("product") ProductDto productDto)
                            {
      Product product =productService.save(productDto);
       return ResponseEntity.ok(product);
    }
    @GetMapping("/showProduct")
    public List<Product>showAllProduct(){
        return productService.findAll();
    }
    @GetMapping("/productbyId/{id}")
    public ResponseEntity<Product> productFindById(@PathVariable Long id){
        Product product=productService.findById(id);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@ModelAttribute("product") ProductDto productDto){
        Product product=productService.updateProduct(id,productDto);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @GetMapping("/deleteProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id){
        Product product=productService.deletebyId(id);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);

    }
   @GetMapping("/findProductByCategory/{id}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable Long id){
        List<Product>products;
        if(id==0 || id==null){
            products=productService.findAll();
        }
        else {
            products = productService.findByCategory(id);
        }
        if(products==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
   }


}
