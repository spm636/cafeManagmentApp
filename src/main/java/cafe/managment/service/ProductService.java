package cafe.managment.service;

import cafe.managment.dto.ProductDto;
import cafe.managment.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product save(ProductDto productDto);
    List<Product>findAll();

    List<Product> findByCategory(Long id);

    Product findById(Long id);

    Product deletebyId(Long id);

    Product updateProduct(Long id,ProductDto productDto);
}
