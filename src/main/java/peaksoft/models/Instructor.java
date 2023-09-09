package peaksoft.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Role;
import peaksoft.validation.PhoneNumberValidation;

import java.time.ZonedDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "instructors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq",allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    @PhoneNumberValidation
    private String phoneNumber;
    private String specialization;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format")
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    @ManyToMany(cascade ={PERSIST,DETACH,REFRESH,MERGE})
    private List<Company>companies;
    @OneToOne(cascade = ALL,mappedBy = "instructor")
    private User user;

    @Enumerated(EnumType.STRING)
//    @ColumnDefault("INSTRUCTOR")
    private Role role;

    @OneToMany(mappedBy = "instructor")
    List<Course>courses;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}