package softuni.medident.service.services;

import softuni.medident.service.models.DentistServiceModel;

import java.util.List;

public interface DentistService {

    void create(DentistServiceModel serviceModel);

    List<DentistServiceModel> getAll();
}
