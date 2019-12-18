package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Dentist;
import softuni.medident.data.repositories.DentistRepository;
import softuni.medident.service.models.DentistServiceModel;
import softuni.medident.service.services.DentistService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository, ModelMapper modelMapper) {
        this.dentistRepository = dentistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(DentistServiceModel serviceModel) {
        Dentist dentist = this.modelMapper.map(serviceModel, Dentist.class);
        this.dentistRepository.saveAndFlush(dentist);
    }

    @Override
    public List<DentistServiceModel> getAll() {
        return this.dentistRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DentistServiceModel.class))
                .collect(Collectors.toList());
    }
}
