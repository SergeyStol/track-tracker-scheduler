package dev.sstol.tasktrackerscheduler.features.resultanalyzer;

import dev.sstol.tasktrackerscheduler.features.tasks.TaskService;
import dev.sstol.tasktrackerscheduler.infrastructure.cron.CronTimestampParser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Sergey Stol
 * 2024-10-14
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResultAnalyzerService {
   private final TaskService taskService;
   private final CronTimestampParser cronTimestampParser;

   static final String analyzeTasksCronExpression = "0 45 10 * * ?";

   @Scheduled(cron = analyzeTasksCronExpression)
//   @Scheduled(cron = "@daily")
   public void analyzeTasks() {
      Set<String> owners = taskService.findOwners();
      Timestamp cronTimestamp = cronTimestampParser.parse(analyzeTasksCronExpression);
      
      for (String owner : owners) {
//         taskRepository.findByOwnerAndCompletedDateBetweenOrCompletedDateNull(owner, in);
      }
   }
}
