package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import softuni.medident.service.models.base.BaseModel;

@Getter
@Setter
@NoArgsConstructor
public class UserEditModel extends BaseModel {

    private Integer age;
    private String phoneNumber;
    private MultipartFile image;
    private Integer number;
    private String street;
    private String city;
    private String postcode;

}
