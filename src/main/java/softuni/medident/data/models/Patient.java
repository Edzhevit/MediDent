package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends User {

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @OneToOne(targetEntity = Address.class)
    private Address address;
    @ManyToOne(targetEntity = Dentist.class)
    private Dentist dentist;
    @OneToMany(targetEntity = PatientHistory.class, mappedBy = "patient")
    private List<PatientHistory> patientHistories;

}
