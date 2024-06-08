package cafe.managment.repository;

import cafe.managment.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c FROM Cart c WHERE c.isActive=true order by c.id")
     List<Cart> activatedItem();
    @Query("SELECT c FROM Cart c WHERE c.isActive=true AND c.product.id=?1 ")
    Cart findProductFromCart(Long id);
    @Query("select COALESCE(sum (c.totel),0) from Cart  c where c.isActive=true ")
    double grandTotel();
}
