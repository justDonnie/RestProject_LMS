package peaksoft.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

@Getter
@Setter

public class StudentRequest {

    private String firstName;
    private String lastName;
    @PhoneNumberValidation
    private String phoneNumber;
    @Email
    private String email;
    private String password;
    private String studyFormat;
    private boolean isBlocked;

}
