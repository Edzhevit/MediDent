package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.Address;
import softuni.medident.data.models.Dentist;
import softuni.medident.data.models.PatientHistory;

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
    private Address address;
    private Dentist dentist;
    private List<PatientHistory> patientHistories;

}
