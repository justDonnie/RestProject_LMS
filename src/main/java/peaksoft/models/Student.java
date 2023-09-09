package peaksoft.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;
import peaksoft.validation.PhoneNumberValidation;

import java.time.ZonedDateTime;


@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq",allocationSize = 1)
    @Column( nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    @Valid
    @PhoneNumberValidation
    private String phoneNumber;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format")
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String studyFormat;
    private boolean isBlocked;
    @ManyToOne
    private Group group;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "student")
    private User user;

    @Enumerated(EnumType.STRING)
//    @ColumnDefault("STUDENT")
    private Role role;


    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}