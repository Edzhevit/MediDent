package softuni.medident.web.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewModel {

    private String id;
    private String brand;
    private String model;
    private BigDecimal price;
    private MultipartFile image;
}
