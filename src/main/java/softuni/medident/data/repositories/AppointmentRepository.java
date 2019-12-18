package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
