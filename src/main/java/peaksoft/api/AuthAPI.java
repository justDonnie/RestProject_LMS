package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.*;
import peaksoft.services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication API")
public class AuthAPI {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    @Operation(summary = "Sign Up",description = "To sign up fill the all columns")
    AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest){
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/reg")
    SimpleResponse uniRegistration(@RequestBody UniRegistrRequest uniRegistrRequest){
        return authenticationService.uniRegistr(uniRegistrRequest);
    }

}
