package org.khasanof.batch52.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.scheduler
 * @since 12/31/2024 10:01 PM
 */
@Component
public class RealBankJobScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;

    public RealBankJobScheduler(@Qualifier("bankJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    /**
     *
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobParametersInvalidException
     * @throws JobRestartException
     */
    @Scheduled(fixedDelay = 25000)
    void execute() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job, jobParameters);
    }
}
