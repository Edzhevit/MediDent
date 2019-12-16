package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String gender;
//    private MultipartFile image;
}
