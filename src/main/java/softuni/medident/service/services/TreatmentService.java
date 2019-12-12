package softuni.medident.service.services;

import softuni.medident.service.models.TreatmentServiceModel;

import java.util.List;

public interface TreatmentService {

    List<TreatmentServiceModel> getAllTreatments();

    void createTreatment(TreatmentServiceModel serviceModel);

    TreatmentServiceModel getById(String id);
}
