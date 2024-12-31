package org.khasanof.springbatch.config;

import lombok.AllArgsConstructor;
import org.khasanof.springbatch.entity.Employee;
import org.khasanof.springbatch.repository.EmployeeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class EmployeeBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private EmployeeRepository employeeRepository;

    @Bean
    public ItemReader<Employee> reader() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Employee.class);

        return new StaxEventItemReaderBuilder<Employee>()
                .name("employeeReader")
                .resource(new FileSystemResource("src/main/resources/employee.xml"))
                .addFragmentRootElements("employee")
                .unmarshaller(marshaller)
                .build();
    }

    @Bean
    public EmployeeProcessor employeeProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public ItemWriter<Employee> writer() {
        StaxEventItemWriter<Employee> itemWriter = new StaxEventItemWriter<>();
        itemWriter.setRootTagName("employee");
        itemWriter.setResource(new FileSystemResource("src/main/resources/employee.xml"));
        itemWriter.setMarshaller(marshaller());
        return itemWriter;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Employee.class);
        return marshaller;
    }

    @Bean
    public RepositoryItemWriter<Employee> writerEmployee() {
        RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
        writer.setRepository(employeeRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("xml-step").<Employee, Employee>chunk(10).reader(reader())
                .processor(employeeProcessor())
                .writer(writerEmployee()).build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("runJob")
                .flow(step()).end().build();
    }
}
