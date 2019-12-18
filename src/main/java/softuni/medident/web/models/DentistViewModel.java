package softuni.medident.web.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import softuni.medident.service.models.base.BaseModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DentistViewModel extends BaseModel {

    private String name;
    private Integer age;
    private String gender;
    private String specialist;
    private MultipartFile image;
}
