package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq",allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    private String taskName;
    private String taskText;
    private ZonedDateTime deadline;

    @ManyToOne(cascade ={PERSIST,DETACH,REFRESH,MERGE},fetch = FetchType.EAGER)
    private Lesson lesson;
    
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}