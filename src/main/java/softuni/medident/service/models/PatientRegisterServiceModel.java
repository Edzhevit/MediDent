package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.Gender;

@Getter
@Setter
@NoArgsConstructor
public class PatientRegisterServiceModel {

    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;
    private Integer age;
    private Gender gender;
}