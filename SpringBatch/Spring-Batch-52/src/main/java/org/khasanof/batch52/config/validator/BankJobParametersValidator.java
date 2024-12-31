package org.khasanof.batch52.config.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config.validator
 * @since 12/31/2024 9:35 PM
 */
@Slf4j
public class BankJobParametersValidator implements JobParametersValidator {

    /**
     *
     * @param parameters
     * @throws JobParametersInvalidException
     */
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        log.debug("Request to validate : {}", parameters);
        // write your logic...
    }
}
