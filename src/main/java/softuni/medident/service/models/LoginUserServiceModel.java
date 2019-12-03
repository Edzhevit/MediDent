package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.Role;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {

    private String email;
    private String password;
    private Role role;

    public LoginUserServiceModel(String email) {
        this.email = email;
    }
}
