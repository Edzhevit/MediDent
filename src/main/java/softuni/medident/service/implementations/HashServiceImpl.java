package softuni.medident.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.medident.service.services.HashService;

@Service
public class HashServiceImpl implements HashService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public HashServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String hash(String hash) {
        return this.bCryptPasswordEncoder.encode(hash);
    }
}
