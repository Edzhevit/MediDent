package softuni.medident.web.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDetailsModel {

    private String id;
    private String brand;
    private String model;
    private BigDecimal price;
    private String imageUrl;
}
