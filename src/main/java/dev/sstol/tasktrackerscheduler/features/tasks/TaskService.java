package dev.sstol.tasktrackerscheduler.features.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Sergey Stol
 * 2024-10-13
 */
@Service
@RequiredArgsConstructor
public class TaskService {
   private final TaskRepository taskRepository;

   public void add(Task task) {
      taskRepository.save(task);
   }

   public Set<String> findOwners() {
      Set<String> owners = taskRepository.findOwners();
      for (String owner : owners) {
         taskRepository.findByOwnerAndCompletedDateBetweenOrCompletedDateNull(owner, in);
      }
      return null;
   }
}
