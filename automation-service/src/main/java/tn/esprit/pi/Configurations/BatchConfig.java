/*
package tn.esprit.pi.Configurations;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.transaction.PlatformTransactionManager;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchConfig {
    public  JobRepository jobRepository;
    public  PlatformTransactionManager platformTransactionManager;
    public  MetaDataProcessor metaDataProcessor;
    public  MetaDataWriter metaDataWriter;
    public  MetadataItemReader metadataItemReader;
    public Job metadataProcessingJob;


    @Bean
    @StepScope
    public ItemReader<InputStream> metadataItemReader() throws IOException {
        FlatFileItemReader<InputStream> reader = new FlatFileItemReader<>();

        ExecutionContext executionContext = new ExecutionContext();
        reader.open(executionContext);
        reader.setResource(new InputStreamResource(metadataItemReader.getInputFile().getInputStream()));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(InputStream.class);
            }});
        }});

        return reader;
    }

    @Bean
    public Step metadataProcessingStep() throws IOException {
        return new StepBuilder("metadataProcessingStep")
                .<InputStream, Map<String, String>>chunk(10)
                .reader(metadataItemReader())
                .processor(metaDataProcessor)
                .writer(metaDataWriter)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }
    @Bean
    public Job metadataProcessingJob(Step metadataProcessingStep) {
        return new JobBuilder("metadataProcessingJob")
                .start(metadataProcessingStep)
                .build();
    }

}
*/
