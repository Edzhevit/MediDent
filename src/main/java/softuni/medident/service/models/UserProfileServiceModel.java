package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.Appointment;
import softuni.medident.service.models.base.BaseModel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileServiceModel extends BaseModel {

    private String username;
    private String password;
    private String email;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private AddressServiceModel address;
    private String imageUrl;
    private List<Appointment> appointments;
}
