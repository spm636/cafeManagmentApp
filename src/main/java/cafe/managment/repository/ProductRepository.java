package cafe.managment.repository;

import cafe.managment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.isActivated=true ")
    List<Product> findByActivated();

    @Query("select p from Product p where p.isActivated=true and p.category.id=?1 ")
    List<Product> findBycategory(Long id);
}
