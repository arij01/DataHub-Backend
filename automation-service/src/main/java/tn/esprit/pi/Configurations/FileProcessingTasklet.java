/*
package tn.esprit.pi.Configurations;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.services.FileProcessingService;
@Configuration

public class FileProcessingTasklet implements Tasklet {

    private final FileProcessingService fileProcessingService;
    private String fileName;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private StepExecution stepExecution;

    @Autowired
    public FileProcessingTasklet(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Call your file processing service to process the file
        fileProcessingService.processFile(fileName);

        // Return RepeatStatus.FINISHED to indicate that the tasklet has completed
        return RepeatStatus.FINISHED;
    }
}
*/
