package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class JobApplicationViewModel {

    private String name;
    private String location;
    private String description;
    private BigDecimal salary;

}
