package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(targetEntity = Address.class)
    private Address address;

    @ManyToOne(targetEntity = Dentist.class)
    private Dentist dentist;

    @OneToMany(targetEntity = PatientHistory.class, mappedBy = "patient")
    private List<PatientHistory> patientHistories;

}
