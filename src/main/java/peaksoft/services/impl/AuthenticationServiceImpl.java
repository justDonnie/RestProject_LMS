package peaksoft.services.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.*;
import peaksoft.enums.Role;
import peaksoft.exceptions.AlreadyExistException;
import peaksoft.exceptions.BadCredentialException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Instructor;
import peaksoft.models.Student;
import peaksoft.models.User;
import peaksoft.repositories.InstructorRepository;
import peaksoft.repositories.StudentRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.security.jwt.JwtService;
import peaksoft.services.AuthenticationService;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new AlreadyExistException(
                    "User with email " + signUpRequest.email() + " is already exists!"
            );
        }
        User user = User.builder()
                .performer(signUpRequest.performer())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role())
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email())
                .orElseThrow(() -> new NotFoundException(
                        "User with  email " + signInRequest.email() + " is not found"
                ));
        if (signInRequest.email().isBlank()) {
            throw new BadCredentialException("Email is blanked!!!");
        }
        if (!passwordEncoder.matches(signInRequest.password(),user.getPassword())) {
            throw new BadCredentialException("Wrong password!!!");
        }
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }



    @PostConstruct
    @Override
    public void initMethod() {
        User user = new User();
        user.setPerformer("Admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin1"));
        user.setRole(Role.ADMIN);
        if (!userRepository.existsByEmail("admin@gmail.com")){
            userRepository.save(user);
        }
    }

    @Override
    public SimpleResponse uniRegistr(UniRegistrRequest uniRegistrRequest) {
        if (uniRegistrRequest.role().equals(Role.INSTRUCTOR)){
            Instructor instructor = distributeInstructor(uniRegistrRequest);
            User user = distributeUser(uniRegistrRequest);
            instructor.setUser(user);
            user.setInstructor(instructor);
            userRepository.save(user);
            instructorRepository.save(instructor);
        } else if (uniRegistrRequest.role().equals(Role.STUDENT)) {
            Student student = distributeStudent(uniRegistrRequest);
            User user = distributeUser(uniRegistrRequest);
            student.setUser(user);
            user.setStudent(student);
          //  userRepository.save(user);
            studentRepository.save(student);
        }
        else {
            throw new BadCredentialException("Input the correct Role of User");
        }
        return new SimpleResponse(
                HttpStatus.OK,
                "User is successfully registered!!!"
        );
    }


    private User distributeUser(UniRegistrRequest uniRegistrRequest) {
        User user = new User();
        user.setPerformer(uniRegistrRequest.performer());
        user.setEmail(uniRegistrRequest.email());
        user.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        user.setRole(uniRegistrRequest.role());
        return user;
    }

    private Instructor distributeInstructor(UniRegistrRequest uniRegistrRequest){
        Instructor instructor = new Instructor();
        instructor.setFirstName(uniRegistrRequest.firstName());
        instructor.setLastName(uniRegistrRequest.lastName());
        instructor.setSpecialization(uniRegistrRequest.specialization());
        instructor.setPhoneNumber(uniRegistrRequest.phoneNumber());
        instructor.setEmail(uniRegistrRequest.email());
        instructor.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        instructor.setCreatedAt(ZonedDateTime.now());
        instructor.setRole(uniRegistrRequest.role());
        return instructor;
    }

    private Student distributeStudent(UniRegistrRequest uniRegistrRequest){
        Student student = new Student();
        student.setFirstName(uniRegistrRequest.firstName());
        student.setLastName(uniRegistrRequest.lastName());
        student.setPhoneNumber(uniRegistrRequest.phoneNumber());
        student.setEmail(uniRegistrRequest.email());
        student.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        student.setStudyFormat(uniRegistrRequest.studyFormat());
        student.setBlocked(uniRegistrRequest.isBlocked());
        student.setCreatedAt(ZonedDateTime.now());
        student.setRole(uniRegistrRequest.role());
        return student;
    }



}
