package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {

    private String email;
    private String password;

    public LoginUserServiceModel(String email) {
        this.email = email;
    }
}
