package cafe.managment.serviceImpl;

import cafe.managment.dto.DailyReport;
import cafe.managment.dto.OrderDto;
import cafe.managment.model.Cart;
import cafe.managment.model.Order;
import cafe.managment.model.OrderDetails;
import cafe.managment.repository.CartRepository;
import cafe.managment.repository.OrderDetailsRepository;
import cafe.managment.repository.OrderRepository;
import cafe.managment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Order saveOrder(OrderDto orderDto) {
        Order order=new Order();
        order.setCustomerName(orderDto.getCustomerName());
        order.setMobile(orderDto.getMobile());
        order.setPlace(orderDto.getPlace());
        order.setOrderDate(LocalDate.now());
        order.setAccept(true);
        order.setPaymentMethod("cash");
        orderRepository.save(order);
        order.setGrandTotelPrize(cartRepository.grandTotel());
        List<Cart> cart=cartRepository.activatedItem();
        for(Cart c:cart){
            OrderDetails orderDetails=new OrderDetails();
            orderDetails.setProduct(c.getProduct());
            orderDetails.setQuantity(c.getQuanity());
            orderDetails.setTotalPrice(c.getTotel());
            orderDetails.setUnitPrice(c.getProduct().getSalePrice());
            orderDetails.setOrder(order);
            orderDetailsRepository.save(orderDetails);
            c.setActive(false);
            cartRepository.save(c);
        }
        return order;
    }

    @Override
    public List<OrderDetails> findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<DailyReport> dailyReports(LocalDate date) {
   List<Object[]> objects=orderDetailsRepository.dailyReport(date);
   List<DailyReport> dailyReports=new ArrayList<>();
     for(Object[] row:objects ){
          String product= (String) row[0];
         Long quantityLong = (Long) row[1];
         int quantity = quantityLong.intValue();
         //Integer quantity= (Integer) row[1];
          Double totelPrice= (Double) row[2];
   dailyReports.add(new DailyReport(product,quantity,totelPrice));

     }

        return dailyReports;
    }

    @Override
    public Double dailyProfit(LocalDate date) {
        return orderRepository.totelProfit(date);
    }

    @Override
    public int noOfCustomer(LocalDate localDate) {
        return orderRepository.noOfCustomer(localDate);
    }
}
