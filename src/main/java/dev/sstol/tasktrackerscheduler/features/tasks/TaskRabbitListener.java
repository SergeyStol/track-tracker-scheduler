package dev.sstol.tasktrackerscheduler.features.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static dev.sstol.tasktrackerscheduler.features.tasks.TaskQueuesNames.TASK_CREATED_QUEUE_NAME;
import static dev.sstol.tasktrackerscheduler.features.tasks.TaskQueuesNames.TASK_UPDATED_QUEUE_NAME;

/**
 * @author Sergey Stol
 * 2024-10-13
 */
@Service
@RequiredArgsConstructor
public class TaskRabbitListener {
   private final ObjectMapper objectMapper;
   private final TaskService taskService;

   @RabbitListener(queues = TASK_CREATED_QUEUE_NAME)
   public void receiveMessageTaskCreated(String message) throws JsonProcessingException {
      System.out.println("Received <" + message + ">");
      Task task = objectMapper.readValue(message, Task.class);
      taskService.add(task);
   }

   @RabbitListener(queues = TASK_UPDATED_QUEUE_NAME)
   public void receiveMessageTaskUpdated(String message) throws JsonProcessingException {
      System.out.println("Received <" + message + ">");
      Task task = objectMapper.readValue(message, Task.class);
      taskService.add(task);
   }
}
