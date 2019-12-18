package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileModel {

    private String username;
    private String password;
    private String email;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private AddressViewModel address;
    private List<AppointmentViewModel> appointments;
    private String imageUrl;

}
