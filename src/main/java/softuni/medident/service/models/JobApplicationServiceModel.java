package softuni.medident.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationServiceModel {

    private String id;
    private String name;
    private String location;
    private String description;
    private BigDecimal salary;

}
