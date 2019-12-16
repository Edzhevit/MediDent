package softuni.medident.web.models;

import lombok.*;
import softuni.medident.service.models.base.BaseModel;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDetailsModel extends BaseModel {

    private String brand;
    private String model;
    private BigDecimal price;
    private String imageUrl;
    private boolean isOwned;
}
