package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, String> {
}
