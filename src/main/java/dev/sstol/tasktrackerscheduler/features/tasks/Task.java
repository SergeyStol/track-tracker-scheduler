package dev.sstol.tasktrackerscheduler.features.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author Sergey Stol
 * 2024-10-13
 */
@Getter
@Setter
@Entity
@Table(name = "tasks", indexes = {
  @Index(name = "owner_idx", columnList = "owner")
})
public class Task {
   @Id
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "owner", nullable = false)
   private String owner;

   @Column(name = "title", nullable = false)
   private String title;

   @Column(name = "completed_date")
   private Timestamp completedDate;
}
