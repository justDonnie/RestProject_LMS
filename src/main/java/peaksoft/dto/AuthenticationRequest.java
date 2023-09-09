package peaksoft.dto;

import peaksoft.enums.Role;

public record AuthenticationRequest(
        String performer,
        String email,
        String password,
        Role role
) {
}
