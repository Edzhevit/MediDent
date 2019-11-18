package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
