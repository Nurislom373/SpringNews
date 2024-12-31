package org.khasanof.batch52.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config
 * @since 12/31/2024 10:10 PM
 */
@Configuration
@EnableBatchProcessing(
        dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager",
        tablePrefix = "BATCH52_",
        maxVarCharLength = 1000,
        isolationLevelForCreate = "SERIALIZABLE"
)
public class BatchH2DataSourceConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public DataSource batchDataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    /**
     *
     * @return
     */
    @Bean
    public ResourcelessTransactionManager batchTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(batchDataSource());
        factory.setTransactionManager(batchTransactionManager());
        factory.setDatabaseType(DatabaseType.H2.getProductName());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
