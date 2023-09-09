package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Course;
import peaksoft.models.Instructor;
import peaksoft.validation.PhoneNumberValidation;


import java.time.ZonedDateTime;
import java.util.List;


@Getter
@Setter

public class CompanyResponse {
    private Long id;
    private String name;
    private String country;
    private String address;
    @PhoneNumberValidation
    private String phoneNumber;
    List<Instructor> instructors;
    List<Course>courses;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public CompanyResponse(Long id, String name, String country, String address, String phoneNumber, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
