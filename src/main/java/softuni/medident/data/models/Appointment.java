package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment extends BaseEntity {

    @Column
    private String reason;
    @Column
    private String dentistName;
    @Column
    private String treatmentType;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
