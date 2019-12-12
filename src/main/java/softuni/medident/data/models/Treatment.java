package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "treatments")
@Getter
@Setter
@NoArgsConstructor
public class Treatment extends BaseEntity{

    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "treatment_type", nullable = false)
//    @Enumerated(EnumType.STRING)
    private String type;
}
