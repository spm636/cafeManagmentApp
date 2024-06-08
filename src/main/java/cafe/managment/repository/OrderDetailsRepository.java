package cafe.managment.repository;

import cafe.managment.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {

    @Query("select o.product.name as product,sum(o.quantity) as quantity,sum(o.totalPrice)as totelPrice  FROM OrderDetails o where o.order.orderDate=?1 GROUP BY o.product.name")
    List<Object[]> dailyReport(LocalDate date);
}
