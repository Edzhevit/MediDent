package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dentists")
@Getter
@Setter
@NoArgsConstructor
public class Dentist extends BaseEntity{

    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "qualification", nullable = false)
    private String qualification;
    @OneToOne(targetEntity = Address.class)
    private Address address;
    @OneToMany(mappedBy = "dentist", targetEntity = User.class)
    private List<User> users;

}
