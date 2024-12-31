package org.khasanof.batch52.config;

import org.khasanof.batch52.config.flow.processor.RealBankItemProcessor;
import org.khasanof.batch52.config.flow.writer.RealBankItemWriter;
import org.khasanof.batch52.config.listener.BankExecutionListener;
import org.khasanof.batch52.config.validator.BankJobParametersValidator;
import org.khasanof.batch52.model.BankCsvDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config
 * @since 12/31/2024 9:27 PM
 */
@Configuration
public class BankJobConfiguration {

    /**
     *
     * @param jobRepository
     * @return
     */
    @Bean
    public Job bankJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("bankJob", jobRepository)
                .preventRestart()
                .listener(new BankExecutionListener())
                .validator(new BankJobParametersValidator())
                .start(bankStep(jobRepository, platformTransactionManager))
                .build();
    }

    /**
     *
     * @param jobRepository
     * @return
     */
    public Step bankStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("bankStep1", jobRepository)
                .<BankCsvDTO, BankCsvDTO>chunk(100, platformTransactionManager)
                .reader(realBankItemReader())
                .writer(realBankItemWriter())
                .processor(realBankItemProcessor())
                .build();
    }

    /**
     *
     * @return
     */
    public ItemReader<BankCsvDTO> realBankItemReader() {
        FlatFileItemReader<BankCsvDTO> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        reader.setName("RealBankItemReader");
        reader.setResource(new ClassPathResource("real-banks.csv"));
        return reader;
    }

    /**
     *
     * @return
     */
    public ItemWriter<BankCsvDTO> realBankItemWriter() {
        return new RealBankItemWriter();
    }

    /**
     *
     * @return
     */
    public ItemProcessor<BankCsvDTO, BankCsvDTO> realBankItemProcessor() {
        return new RealBankItemProcessor();
    }

    /**
     *
     * @return
     */
    private LineMapper<BankCsvDTO> lineMapper() {
        DefaultLineMapper<BankCsvDTO> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("mfo", "name", "address", "city");

        BeanWrapperFieldSetMapper<BankCsvDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(BankCsvDTO.class);

        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
