package softuni.medident.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.service.models.base.BaseModel;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductServiceModel extends BaseModel {

    private String brand;
    private String model;
    private BigDecimal price;
    private String imageUrl;
    private boolean isOwned;
}
