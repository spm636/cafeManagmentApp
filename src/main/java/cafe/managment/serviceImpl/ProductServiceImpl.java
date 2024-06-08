package cafe.managment.serviceImpl;

import cafe.managment.dto.ProductDto;
import cafe.managment.model.Category;
import cafe.managment.model.Product;
import cafe.managment.repository.CategoryRepository;
import cafe.managment.repository.ProductRepository;
import cafe.managment.service.ProductService;
import cafe.managment.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageUpload imageUpload;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Product save(ProductDto productDto) {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCostPrice(productDto.getCostPrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setNoOfQuantity(productDto.getNoOfQuantity());
        product.setActivated(true);
        Category category=categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        product.setCategory(category);
      product.setImagesUrl(imageUpload.uploadToLocalAndReadyImages(productDto.getImagesUrl()));
    return productRepository.save(product);
    }




    @Override
    public List<Product> findAll() {
        return productRepository.findByActivated();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product deletebyId(Long id) {
        Product product=productRepository.getReferenceById(id);
        product.setActivated(false);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto ) {
        Product product=productRepository.getReferenceById(id);
        product.setName(productDto.getName());
        product.setCostPrice(productDto.getCostPrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setNoOfQuantity(productDto.getNoOfQuantity());
        if (imageUpload.uploadToLocalAndReadyImages(productDto.getImagesUrl()).isEmpty())







        {
            product.setImagesUrl(product.getImagesUrl());


        }
        else {
            product.setImagesUrl(imageUpload.uploadToLocalAndReadyImages(productDto.getImagesUrl()));
        }
        if(productDto.getCategoryId()==0){
            product.setCategory(product.getCategory());
        }
        else {
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByCategory(Long id) {
        return productRepository.findBycategory(id);
    }
}
