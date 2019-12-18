package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressViewModel {

    private Integer number;
    private String street;
    private String city;
    private String postcode;
}
