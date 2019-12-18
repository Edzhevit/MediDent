package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Appointment;
import softuni.medident.data.repositories.AppointmentRepository;
import softuni.medident.service.models.AppointmentServiceModel;
import softuni.medident.service.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void makeAppointment(AppointmentServiceModel serviceModel) {
        Appointment appointment = this.modelMapper.map(serviceModel, Appointment.class);
        this.appointmentRepository.saveAndFlush(appointment);
    }

}
