package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Boolean existsByEmail(String email);

    User findByUsername(String username);

//    Boolean existsByImageUrl(String imageUrl);
}
