package softuni.medident.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.medident.constants.Constants;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.repositories.JobApplicationRepository;
import softuni.medident.exception.JobNotFoundException;
import softuni.medident.service.base.ServiceTestBase;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationService;
import softuni.medident.service.services.JobApplicationValidatorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobApplicationServiceTest extends ServiceTestBase {

    List<JobApplication> jobs;

    @MockBean
    JobApplicationRepository repository;

    @MockBean
    JobApplicationValidatorService validatorService;

    @Autowired
    JobApplicationService service;

    @Override
    protected void beforeEach(){
        jobs = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(jobs);
    }

    @Test
    void createJob_whenInputIsValid_shouldCreateJob() throws JobNotFoundException {
        JobApplicationServiceModel serviceModel = new JobApplicationServiceModel(Constants.DEFAULT_ID, Constants.DEFAULT_USERNAME,
                Constants.DEFAULT_LOCATION, Constants.DEFAULT_DESCRIPTION, Constants.DEFAULT_SALARY);

        Mockito.when(validatorService.isValid(serviceModel))
                .thenReturn(true);

        service.createJob(serviceModel);

        ArgumentCaptor<JobApplication> argument = ArgumentCaptor.forClass(JobApplication.class);
        Mockito.verify(repository).saveAndFlush(argument.capture());

        JobApplication jobApplication = argument.getValue();
        assertNotNull(jobApplication);

    }

    @Test
    void createJob_whenInputIsNotValid_shouldThrow() {
        JobApplicationServiceModel serviceModel = new JobApplicationServiceModel(null, Constants.DEFAULT_USERNAME,
                Constants.DEFAULT_LOCATION, null, Constants.DEFAULT_SALARY);

        Mockito.when(validatorService.isValid(serviceModel))
                .thenReturn(false);

        assertThrows(
                JobNotFoundException.class,
                () -> service.createJob(serviceModel));

    }

    @Test
    void getAllJobs_WhenNoJobs_shouldReturnEmptyList(){
        jobs.clear();
        List<JobApplicationServiceModel> actualJobs = service.getAllJobs();
        assertEquals(jobs.size(), actualJobs.size());
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
        String id = Constants.DEFAULT_ID;
        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(Constants.DEFAULT_ID);

        Mockito.when(repository.getById(id)).thenReturn(jobApplication);

        JobApplicationServiceModel serviceModel = service.getById(id);
        assertEquals(jobApplication.getId(), serviceModel.getId());
    }

    @Test
    void getById_WhenJobDoesNOTExist_shouldThrowJobNotFoundException() {
        String id = Constants.DEFAULT_ID;

        Mockito.when(repository.getById(id)).thenReturn(null);

        assertThrows(JobNotFoundException.class, () -> service.getById(id));
    }

    @Test
    void remove_whenThereIsJob_shouldRemoveCorrectly() throws JobNotFoundException {
        String id = Constants.DEFAULT_ID;
        JobApplication jobApplication = new JobApplication(Constants.DEFAULT_USERNAME,
                Constants.DEFAULT_LOCATION, Constants.DEFAULT_DESCRIPTION, Constants.DEFAULT_SALARY);

        Mockito.when(repository.getById(id))
                .thenReturn(jobApplication);

        service.removeJob(id);

        ArgumentCaptor<JobApplication> argument = ArgumentCaptor.forClass(JobApplication.class);
        Mockito.verify(repository).delete(argument.capture());

        JobApplication job = argument.getValue();
        assertNotNull(job);
    }

    @Test
    void remove_whenThereIsNoJob_shouldThrow() throws JobNotFoundException {
        String id = Constants.DEFAULT_ID;

        Mockito.when(repository.getById(id))
                .thenReturn(null);

        assertThrows(JobNotFoundException.class, () -> service.removeJob(id));
    }

    private List<JobApplication> getJobs(){
        List<JobApplication> jobs = new ArrayList<>();
        JobApplication job = new JobApplication(Constants.DEFAULT_USERNAME, Constants.DEFAULT_LOCATION,
                Constants.DEFAULT_DESCRIPTION, Constants.DEFAULT_SALARY);
        JobApplication job2 = new JobApplication(Constants.DEFAULT_USERNAME, Constants.DEFAULT_LOCATION,
                Constants.DEFAULT_DESCRIPTION, Constants.DEFAULT_SALARY);
        jobs.add(job);
        jobs.add(job2);
        return jobs;
    }
}
