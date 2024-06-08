package cafe.managment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private Long id;

    private String name;
    private Double costPrice;
    private Double salePrice;
    private int noOfQuantity;
    private boolean isActivated;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    @NonNull
    @ElementCollection
    @CollectionTable(name = "product_images_url", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> imagesUrl;

}
