package peaksoft.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq",allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Invalid company name format")
    @Column(unique = true, nullable = false)
    private String name;
    private String country;
    private String address;
    @PhoneNumberValidation
    private String phoneNumber;

    @ManyToMany(mappedBy = "companies")
    List<Instructor>instructors;

    @OneToMany(mappedBy = "company")
    List<Course>courses;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Company(String name, String country, String address, String phoneNumber, List<Instructor> instructors, List<Course> courses, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.instructors = instructors;
        this.courses = courses;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}