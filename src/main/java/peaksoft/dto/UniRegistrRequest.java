package peaksoft.dto;

import lombok.Builder;
import peaksoft.enums.Role;


@Builder
public record UniRegistrRequest(
        String performer,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        String specialization,
        String studyFormat,
        boolean isBlocked,
        Role role
) {

}
