package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.service.models.base.BaseModel;

@Getter
@Setter
@NoArgsConstructor
public class DentistViewDetailsModel extends BaseModel {

    private String name;
    private Integer age;
    private String gender;
    private String specialist;
    private String imageUrl;
}
