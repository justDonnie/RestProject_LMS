package peaksoft.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    @PhoneNumberValidation
    private String phoneNumber;
    @Email
    private String email;
    private String password;
    private String studyFormat;
    private boolean isBlocked;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public StudentResponse(Long id, String firstName, String lastName, String phoneNumber, String email, String password, String studyFormat, boolean isBlocked, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.studyFormat = studyFormat;
        this.isBlocked = isBlocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
