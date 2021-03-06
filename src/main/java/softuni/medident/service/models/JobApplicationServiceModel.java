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
public class JobApplicationServiceModel extends BaseModel {

    private String name;
    private String location;
    private String description;
    private BigDecimal salary;

}
