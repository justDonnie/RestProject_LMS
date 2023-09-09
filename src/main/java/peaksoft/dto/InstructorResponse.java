package peaksoft.dto;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    @PhoneNumberValidation
    private String phoneNumber;
    @Email
    private String email;
    private String password;
    private String specialization;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public InstructorResponse(Long id, String firstName, String lastName, String phoneNumber, String email, String password, String specialization, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.specialization = specialization;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
