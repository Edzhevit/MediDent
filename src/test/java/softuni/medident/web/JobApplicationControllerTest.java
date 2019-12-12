package softuni.medident.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.medident.constants.Constants;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.repositories.JobApplicationRepository;
import softuni.medident.web.base.ViewTestBase;
import softuni.medident.web.controllers.JobApplicationController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class JobApplicationControllerTest extends ViewTestBase {

    @MockBean
    JobApplicationRepository repository;

    @Test
    void getJobs_whenThereIsJobs_shouldReturnAllJobsViewWith200() throws Exception {

        mockMvc.perform(get("/careers/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("careers/all-jobs.html"));
    }

    @Test
    void getJobDetails_whenThereIsJob_shouldReturnJobViewWith200() throws Exception {

        String id = Constants.DEFAULT_ID;
        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(id);
        Mockito.when(repository.getById(id))
                .thenReturn(jobApplication);
        mockMvc.perform(get("/careers/details/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name(JobApplicationController.JOB_DETAILS_VIEW_NAME));
    }

    @Test
    void getJobDetails_whenNoJobWithId_shouldReturnErrorPageWith404() throws Exception {
        String id = Constants.DEFAULT_ID;
        mockMvc.perform(get("/careers/details/" + id))
                .andExpect(view().name("error"));
    }
}
