package cafe.managment.dto;

import cafe.managment.model.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Data
public class ProductDto {
    private Long id;

    private String name;
    private Double costPrice;
    private Double salePrice;
    private int noOfQuantity;
    private boolean isActivated;

    private Long categoryId;

   private List<MultipartFile> imagesUrl;
}
