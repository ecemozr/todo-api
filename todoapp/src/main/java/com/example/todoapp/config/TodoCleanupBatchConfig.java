package com.example.todoapp.config;

import com.example.todoapp.tasklet.DeleteCompletedTodosTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class TodoCleanupBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final DeleteCompletedTodosTasklet tasklet;

    // STEP
    @Bean
    public Step deleteCompletedTodosStep() {
        return new StepBuilder("deleteCompletedTodosStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    // JOB
    @Bean
    public Job deleteCompletedTodosJob() {
        return new JobBuilder("deleteCompletedTodosJob", jobRepository)
                .start(deleteCompletedTodosStep())
                .build();
    }
}
