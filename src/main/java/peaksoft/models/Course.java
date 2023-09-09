package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq",allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    @ManyToOne(cascade ={PERSIST,DETACH,REFRESH,MERGE})
    private Company company;

    @ManyToMany(cascade ={PERSIST,DETACH,REFRESH,MERGE})
    List<Group>groups;

    @ManyToOne(cascade ={PERSIST,DETACH,REFRESH,MERGE})
    private Instructor instructor;

    @OneToMany(mappedBy = "course",cascade = ALL)
    List<Lesson>lessons;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}