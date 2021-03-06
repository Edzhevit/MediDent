package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {

    private String username;
    private String password;

    public LoginUserServiceModel(String username) {
        this.username = username;
    }
}
