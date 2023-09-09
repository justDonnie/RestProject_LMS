package peaksoft.models;

import ch.qos.logback.core.net.server.ServerRunner;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq",allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    @ManyToMany(mappedBy = "groups",
            cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.REFRESH})
    List<Course>courses;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    List<Student>students;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}