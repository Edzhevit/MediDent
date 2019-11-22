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
public class Dentist extends User{

    @Column(name = "qualification", nullable = false)
    private String qualification;
    @OneToOne(targetEntity = Address.class)
    private Address address;
    @OneToMany(mappedBy = "dentist", targetEntity = Patient.class)
    private List<Patient> patients;

}
