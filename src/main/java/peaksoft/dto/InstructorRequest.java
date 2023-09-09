package peaksoft.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Company;
import peaksoft.validation.PhoneNumberValidation;

import java.util.List;

@Getter
@Setter
public class InstructorRequest {

    private String firstName;
    private String lastName;
    @PhoneNumberValidation
    private String phoneNumber;
    @Email
    private String email;
    private String password;
    private String specialization;
    private List<Company> companies;

}
