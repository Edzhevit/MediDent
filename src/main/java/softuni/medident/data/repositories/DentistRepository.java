package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.Dentist;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, String> {
}
