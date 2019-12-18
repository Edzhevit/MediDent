package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.Gender;

@Getter
@Setter
@NoArgsConstructor
public class DentistServiceModel {

    private String name;
    private Integer age;
    private Gender gender;
    private String specialist;
    private String imageUrl;
}
