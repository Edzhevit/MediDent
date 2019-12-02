package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.JobApplication;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, String> {

    List<JobApplication> findAll();

    JobApplication getById(String id);
}
