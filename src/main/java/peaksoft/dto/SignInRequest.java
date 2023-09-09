package peaksoft.dto;

public record SignInRequest(
        String email,
        String password
) {
}
