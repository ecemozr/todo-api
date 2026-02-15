package com.example.todoapp.tasklet;

import com.example.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCompletedTodosTasklet implements Tasklet {

    private final TodoRepository todoRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) {

        int deleted = todoRepository.deleteCompletedTodos();

        System.out.println(deleted + " completed todo silindi.");

        return RepeatStatus.FINISHED;
    }
}