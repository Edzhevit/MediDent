package softuni.medident.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.medident.constants.CloudinaryConstants;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    @Value("${cloudinary.api-key}")
    private String apiKey;
    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(CloudinaryConstants.CLOUD_NAME, cloudName);
        config.put(CloudinaryConstants.API_KEY, apiKey);
        config.put(CloudinaryConstants.API_SECRET, apiSecret);

        return new Cloudinary(config);
    }
}
