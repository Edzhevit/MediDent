package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Treatment;
import softuni.medident.data.repositories.TreatmentRepository;
import softuni.medident.service.models.TreatmentServiceModel;
import softuni.medident.service.services.TreatmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, ModelMapper modelMapper) {
        this.treatmentRepository = treatmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TreatmentServiceModel> getAllTreatments() {
        return this.treatmentRepository.findAll().stream()
                .map(t -> this.modelMapper.map(t, TreatmentServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void createTreatment(TreatmentServiceModel serviceModel) {
        Treatment treatment = this.modelMapper.map(serviceModel, Treatment.class);
        this.treatmentRepository.saveAndFlush(treatment);
    }

    @Override
    public TreatmentServiceModel getById(String id) {
        Treatment treatment = this.treatmentRepository.getById(id);

        return this.modelMapper.map(treatment, TreatmentServiceModel.class);
    }
}
