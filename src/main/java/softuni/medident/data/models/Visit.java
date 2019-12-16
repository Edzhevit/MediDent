package softuni.medident.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.medident.data.models.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "visits")
@Getter
@Setter
@NoArgsConstructor
public class Visit extends BaseEntity {

    @Column(name = "visit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VisitType type;
    @Column(name = "action", nullable = false)
    private String action;
    @Column(name = "fee", nullable = false)
    private BigDecimal fee;
}
