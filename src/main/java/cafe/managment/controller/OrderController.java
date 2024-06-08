package cafe.managment.controller;

import cafe.managment.dto.OrderDto;
import cafe.managment.model.Order;
import cafe.managment.model.OrderDetails;
import cafe.managment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    ResponseEntity<Order> saveOrder(@RequestBody OrderDto orderDto){
        Order order=orderService.saveOrder(orderDto);
        if(order==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }
    @GetMapping("/orders/{id}")
    ResponseEntity<OrderDetails> findOrder(@PathVariable Long id){
        List<OrderDetails> orderDetails=orderService.findOrderById(id);
        if(orderDetails==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((OrderDetails) orderDetails);
    }

}
