package cafe.managment.service;

import cafe.managment.dto.DailyReport;
import cafe.managment.dto.OrderDto;
import cafe.managment.model.Cart;
import cafe.managment.model.Order;
import cafe.managment.model.OrderDetails;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Order saveOrder(OrderDto orderDto);

    List<OrderDetails> findOrderById(Long id);
    List<DailyReport> dailyReports(LocalDate date);
    Double dailyProfit(LocalDate date);
    int noOfCustomer(LocalDate localDate);

}
