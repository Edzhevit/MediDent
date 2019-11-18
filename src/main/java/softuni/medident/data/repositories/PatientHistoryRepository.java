package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.PatientHistory;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, String> {
}
