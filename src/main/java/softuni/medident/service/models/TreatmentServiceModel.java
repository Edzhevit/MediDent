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
public class TreatmentServiceModel extends BaseModel {

    private BigDecimal price;
    private String type;
    private String description;
}
