package softuni.medident.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.TreatmentType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentServiceModel {

    private String id;
    private BigDecimal price;
    private String type;
}
