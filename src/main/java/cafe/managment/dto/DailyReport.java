package cafe.managment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyReport {

    private String product;
    private int quantity;
    private Double totelPrice;


    public DailyReport(String product,int quantity, Double totelPrice) {
        this.product = product;
        this.totelPrice = totelPrice;
        this.quantity = quantity;
    }
}
