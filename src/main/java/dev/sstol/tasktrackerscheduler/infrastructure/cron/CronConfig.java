package dev.sstol.tasktrackerscheduler.infrastructure.cron;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergey Stol
 * 2024-10-14
 */
@Configuration
public class CronConfig {
   @Bean
   CronParser cronParser() {
      return new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
   }
}
