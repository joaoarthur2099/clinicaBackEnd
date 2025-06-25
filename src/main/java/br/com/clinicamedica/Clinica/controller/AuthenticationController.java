package br.com.clinicamedica.Clinica.controller;

import br.com.clinicamedica.Clinica.dto.AuthRequestDTO;
import br.com.clinicamedica.Clinica.dto.AuthResponseDTO;
import br.com.clinicamedica.Clinica.dto.RegisterDTO;
import br.com.clinicamedica.Clinica.service.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//localhost:8000/clinica/api/auth/register
/*
* {
    "fullName": "user",
    "userName": "user@gmail.com",
    "password": "user"
}
*
* */
// localhost:8000/clinica/api/auth/login
 /*
 * {
    "userName": "user@gmail.com",
    "password": "user"
}
 *
 * */




@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO authRequest) {
        AuthResponseDTO authResponse = authenticationService.login(authRequest);
        return ResponseEntity.ok(new AuthResponseDTO(authResponse.userName(), authResponse.token()));
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO registerData) throws JsonProcessingException {
        authenticationService.register(registerData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
