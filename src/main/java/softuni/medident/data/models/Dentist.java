package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dentists")
@Getter
@Setter
@NoArgsConstructor
public class Dentist extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "qualification", nullable = false)
    private String qualification;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(targetEntity = Address.class)
    private Address address;
    @OneToMany(mappedBy = "dentist", targetEntity = Patient.class)
    private List<Patient> patients;

}
