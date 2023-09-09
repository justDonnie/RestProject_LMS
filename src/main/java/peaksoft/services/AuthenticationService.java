package peaksoft.services;

import peaksoft.dto.*;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

    void initMethod();


    SimpleResponse uniRegistr(UniRegistrRequest uniRegistrRequest);

}
