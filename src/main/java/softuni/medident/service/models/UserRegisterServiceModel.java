package softuni.medident.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.Gender;
import softuni.medident.data.models.Role;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterServiceModel {

    @NotEmpty
    @Size(min = 2)
    private String username;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String confirmPassword;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @Min(value = 18)
    private Integer age;

    @NotNull
    private Gender gender;

    private Set<RoleServiceModel> authorities;
}
