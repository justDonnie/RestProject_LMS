package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq",allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    private String lessonName;

    @ManyToOne(cascade ={PERSIST,DETACH,REFRESH,MERGE})
    private Course course;

    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Task>tasks;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}