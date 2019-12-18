package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.service.models.base.BaseModel;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TreatmentViewModel extends BaseModel {

    private String type;
    private BigDecimal price;
    private String description;
}
