package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
