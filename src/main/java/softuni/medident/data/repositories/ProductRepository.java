package softuni.medident.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product getById(String id);
}
