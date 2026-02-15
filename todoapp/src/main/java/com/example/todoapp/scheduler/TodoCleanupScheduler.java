package com.example.todoapp.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class TodoCleanupScheduler {

    private final JobLauncher jobLauncher;
    private final Job deleteCompletedTodosJob;

    @Scheduled(cron = "0 * * * * ?")
    public void runCleanupJob() {
        try {
            System.out.println(">>> Scheduler tetiklendi, Job başlatılıyor...");

            JobParameters params = new JobParametersBuilder()
                    .addLong("runtime", System.currentTimeMillis()) // Her seferinde farklı parametre vermezsen Batch aynı job sanıp çalıştırmaz
                    .toJobParameters();

            jobLauncher.run(deleteCompletedTodosJob, params);

        } catch (Exception e) {
            System.err.println(">>> Job çalıştırılırken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}