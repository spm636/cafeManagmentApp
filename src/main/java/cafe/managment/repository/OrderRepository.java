package cafe.managment.repository;

import cafe.managment.model.Order;
import cafe.managment.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from OrderDetails o WHERE o.order.id=?1")
    List<OrderDetails> findOrderById(Long id);
    @Query("SELECT sum(o.grandTotelPrize) from Order o where o.orderDate=?1")
    Double totelProfit(LocalDate date);

    @Query("select Count(o) from Order o where o.orderDate=?1")
    int noOfCustomer(LocalDate date);
}
