package softuni.medident.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentServiceModel {

    private String reason;
    private String dentistName;
    private String treatmentType;
    private UserProfileServiceModel user;
}
