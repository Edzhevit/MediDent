package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "patient_histories")
@Getter
@Setter
@NoArgsConstructor
public class PatientHistory extends BaseEntity {

    @OneToOne
    private Visit reason;
    @OneToOne
    private Treatment treatment;
    @ManyToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

}
