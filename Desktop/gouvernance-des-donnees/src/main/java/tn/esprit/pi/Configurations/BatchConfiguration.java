/*
package tn.esprit.pi.Configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;

import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Map;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private MetaDataProcessor metaDataProcessor;

    @Autowired
    private MetaDataWriter metaDataWriter;

    @Autowired
    private CustomItemReader customItemReader;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;




    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);
        factory.setIsolationLevelForCreate("ISOLATION_REPEATABLE_READ");
        factory.setTablePrefix("BATCH_");
        return factory.getObject();
    }

    @Bean
    public Step metadataProcessingStep() throws Exception {
        return new StepBuilder("metadataProcessingStep")
                .<InputStream, Map<String, String>>chunk(1)
                .reader(customItemReader)
                .processor(metaDataProcessor)
                .writer(metaDataWriter)
                .repository(jobRepository())
                .build();
    }

    @Bean
    public Job metadataProcessingJob() throws Exception {
        return new JobBuilder("metadataProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(metadataProcessingStep())
                .build();
    }
}
*/
