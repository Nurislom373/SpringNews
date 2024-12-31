package org.khasanof.batch52.config;

import org.khasanof.batch52.config.flow.processor.PersonItemProcessor;
import org.khasanof.batch52.config.listener.JobCompletionNotificationListener;
import org.khasanof.batch52.model.PersonDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config
 * @since 12/31/2024 10:32 PM
 */
@Configuration
public class PersonBatchProcessConfiguration {

    /**
     *
     * @param jobRepository
     * @param step1
     * @param listener
     * @return
     */
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    /**
     *
     * @param jobRepository
     * @param transactionManager
     * @param reader
     * @param processor
     * @param writer
     * @return
     */
    @Bean
    public Step step1(JobRepository jobRepository, ResourcelessTransactionManager transactionManager,
                      FlatFileItemReader<PersonDTO> reader, PersonItemProcessor processor, JdbcBatchItemWriter<PersonDTO> writer) {
        return new StepBuilder("step1", jobRepository)
                .<PersonDTO, PersonDTO> chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * @return
     */
    @Bean
    public FlatFileItemReader<PersonDTO> reader() {
        return new FlatFileItemReaderBuilder<PersonDTO>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names("firstName", "lastName")
                .targetType(PersonDTO.class)
                .build();
    }

    /**
     *
     * @return
     */
    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    /**
     *
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcBatchItemWriter<PersonDTO> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<PersonDTO>()
                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
}
