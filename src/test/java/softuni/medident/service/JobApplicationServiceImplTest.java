package softuni.medident.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.repositories.JobApplicationRepository;
import softuni.medident.exception.JobNotFoundException;
import softuni.medident.service.implementations.JobApplicationServiceImpl;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobApplicationServiceImplTest {

    List<JobApplication> jobs;
    JobApplicationRepository repository;
    ModelMapper modelMapper;

    JobApplicationService service;

    @BeforeEach
    void setupTest(){
        jobs = new ArrayList<>();
        repository = Mockito.mock(JobApplicationRepository.class);
        modelMapper = new ModelMapper();
        Mockito.when(repository.findAll()).thenReturn(jobs);
        service = new JobApplicationServiceImpl(repository, modelMapper);
    }

//    @Test
//    void createJob_whenInputNotValid_shouldThrow(){
//        JobApplication job = new JobApplication(null, "Valid Location",
//                "Valid Description", new BigDecimal(2000));
//
//        Mockito.when(repository.saveAndFlush(job)).thenThrow(new Exception());
//
//        assertThrows( Exception.class,
//                () -> service.createJob(modelMapper.map(job, JobApplicationServiceModel.class)));
//
//
//
//    }

    @Test
    void getAllJobs_WhenNoJobs_shouldReturnEmptyList(){
        jobs.clear();
        List<JobApplicationServiceModel> actualJobs = service.getAllJobs();
        assertEquals(0, actualJobs.size());
    }

    @Test
    void getAllJobs_WhenAreJobs_shouldReturnList(){
        jobs.clear();
        jobs.addAll(getJobs());
        List<JobApplicationServiceModel> actualJobs = service.getAllJobs();
        assertEquals(jobs.size(), actualJobs.size());
    }

    @Test
    void getById_WhenJobDoesExist_shouldReturnJob() throws JobNotFoundException {
        String id = "1";
        JobApplication jobApplication = new JobApplication();
        jobApplication.setId("1");

        Mockito.when(repository.getById(id)).thenReturn(jobApplication);

        JobApplicationServiceModel serviceModel = service.getById(id);
        assertEquals(jobApplication.getId(), serviceModel.getId());
    }

    @Test
    void getById_WhenJobDoesNOTExist_shouldThrowJobNotFoundException() throws JobNotFoundException {
        String id = "1";

        Mockito.when(repository.getById(id)).thenReturn(null);

        assertThrows(JobNotFoundException.class, () -> service.getById(id));
    }

    private List<JobApplication> getJobs(){
        List<JobApplication> jobs = new ArrayList<>();
        JobApplication job = new JobApplication("Valid Name", "Valid Location",
                "Valid Description", new BigDecimal(2000));
        JobApplication job2 = new JobApplication("Valid Name 2", "Valid Location 2",
                "Valid Description 2", new BigDecimal(3000));
        jobs.add(job);
        jobs.add(job2);
        return jobs;
    }
}
