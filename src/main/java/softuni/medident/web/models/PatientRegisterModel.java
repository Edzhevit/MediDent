package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class PatientRegisterModel {

    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",
            message = "Password must be at least 8 characters long and must contain at least one digit," +
                    " one lower and one upper case symbol, and one special character")
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Enter a valid email")
    private String email;
//    @Pattern(regexp = "/08[789]\\d{7}/", message = "Enter a valid phone number")
    private String phoneNumber;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 100, message = "Age should not be more than 100")
    private Integer age;

    private String gender;
}
