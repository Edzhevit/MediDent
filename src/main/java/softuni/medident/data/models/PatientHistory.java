package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.base.BaseEntity;

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
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
