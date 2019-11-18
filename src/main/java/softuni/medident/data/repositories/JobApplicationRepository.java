package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, String> {
}
