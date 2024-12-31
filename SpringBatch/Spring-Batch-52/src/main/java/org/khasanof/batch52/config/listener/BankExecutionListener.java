package org.khasanof.batch52.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config.listener
 * @since 12/31/2024 9:33 PM
 */
@Slf4j
public class BankExecutionListener implements JobExecutionListener {

    /**
     *
     * @param jobExecution
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.debug("before job execution: {}", jobExecution.getJobId());
    }

    /**
     *
     * @param jobExecution
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.debug("after job execution: {}", jobExecution.getJobId());

        if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
            //job success
        }
        else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            //job failure
        }
    }
}
