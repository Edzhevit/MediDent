package softuni.medident.service.implementations;

import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import softuni.medident.service.services.HashService;

@Service
public class HashServiceImpl implements HashService {

    @Override
    public String hash(String hash) {
        return DigestUtils.sha256Hex(hash);
    }
}
