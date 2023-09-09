package peaksoft.dto;

import peaksoft.enums.Role;

public record SignUpRequest(String performer,
                            String email,
                            String password,
                            Role role
) {
}
