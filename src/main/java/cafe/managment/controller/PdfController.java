package cafe.managment.controller;

import cafe.managment.dto.OrderDto;
import cafe.managment.model.Cart;
import cafe.managment.service.CartService;
import cafe.managment.service.OrderService;
import cafe.managment.serviceImpl.DailyReport;
import cafe.managment.serviceImpl.PdfBill;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PdfController {
    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @PostMapping("/dowloadBill")
  public void dowloadBill(@RequestBody OrderDto orderDto, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_"  + ".pdf";
        response.setHeader(headerkey, headervalue);
        List<Cart> carts=cartService.activedItem();
        Double totel=cartService.GrandTotel();

        PdfBill pdfBill=new PdfBill();
        pdfBill.setCarts(carts);
        pdfBill.setGrandTotel(totel);
        pdfBill.setOrderDto(orderDto);
        pdfBill.generate(response);
    }
    @PostMapping("/dowloadReport")
    public void dowloadReport(@RequestBody OrderDto orderDto, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_"  + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<cafe.managment.dto.DailyReport> dailyReports=orderService.dailyReports(orderDto.getLocalDate());
        DailyReport dailyReport=new DailyReport();
        LocalDate date=LocalDate.now();
        dailyReport.setOrderDto(orderDto);
        dailyReport.setDailyReports(dailyReports);
        Double profit=orderService.dailyProfit(orderDto.getLocalDate());
        dailyReport.setProfit(profit);
        int customer=orderService.noOfCustomer(orderDto.getLocalDate());
        dailyReport.setCustomer(customer);
        dailyReport.generate(response);


    }
}
