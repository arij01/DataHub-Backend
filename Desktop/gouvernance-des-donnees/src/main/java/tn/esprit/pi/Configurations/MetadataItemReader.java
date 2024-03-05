/*
package tn.esprit.pi.Configurations;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.ItemReader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


public class MetadataItemReader implements ItemReader<InputStream> {
    private MultipartFile inputFile;

    @Override
    public InputStream read() throws Exception {
        if (inputFile != null) {
            InputStream inputStream = inputFile.getInputStream();
            inputFile = null; // To ensure that the file is processed only once
            return inputStream;
        }
        return null;
    }

    public MultipartFile getInputFile() {
        return inputFile;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<InputStream> metadataItemReader(
            @Value("#{jobParameters['file']}") MultipartFile inputFile) throws IOException {
        FlatFileItemReader<InputStream> reader = new FlatFileItemReader<>();
        reader.setResource(new InputStreamResource(inputFile.getInputStream()));
        reader.setLineMapper(new DefaultLineMapper<InputStream>() {{
            setLineTokenizer(new DelimitedLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<InputStream>() {{
                setTargetType(InputStream.class);
            }});
        }});
        return reader;
    }

    @Bean
    @Scope("step")
    public ExecutionContextPromotionListener promotionListener() {
        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        listener.setKeys(new String[]{"file"});
        return listener;
    }
}
*/
