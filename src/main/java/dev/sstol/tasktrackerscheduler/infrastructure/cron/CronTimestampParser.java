package dev.sstol.tasktrackerscheduler.infrastructure.cron;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @author Sergey Stol
 * 2024-10-14
 */
@Component
@RequiredArgsConstructor
public class CronTimestampParser {
   private final CronParser cronParser;

   public Timestamp parse(String cronExpression) {
      Cron cron = cronParser.parse(cronExpression);

      ZonedDateTime now = ZonedDateTime.now();
      ExecutionTime executionTime = ExecutionTime.forCron(cron);
      Optional<ZonedDateTime> nextExecution = executionTime.nextExecution(now);

      if (nextExecution.isPresent()) {
         ZonedDateTime nextRunTime = nextExecution.get();
         return Timestamp.from(nextRunTime.toInstant());
      } else {
         System.out.println("Could not calculate the next execution time.");
      }
      throw new IllegalArgumentException();
   }
}
