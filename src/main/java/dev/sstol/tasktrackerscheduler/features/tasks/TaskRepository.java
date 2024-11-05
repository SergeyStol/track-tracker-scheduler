package dev.sstol.tasktrackerscheduler.features.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * @author Sergey Stol
 * 2024-10-13
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
   @Query("select distinct t.owner from Task t")
   Set<String> findOwners();

}
