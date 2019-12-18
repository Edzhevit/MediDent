package softuni.medident.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentViewModel {

    private String reason;
    private String dentistName;
    private String treatmentType;

}
