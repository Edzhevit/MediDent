package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TreatmentViewModel {

    private String id;
    private BigDecimal price;
    private String type;
}
