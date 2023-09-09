package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

@Getter
@Setter

public class CompanyRequest {
    private String name;
    private String country;
    private String address;
    @PhoneNumberValidation
    private String phoneNumber;

}
