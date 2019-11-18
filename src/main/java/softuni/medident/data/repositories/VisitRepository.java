package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, String> {
}
