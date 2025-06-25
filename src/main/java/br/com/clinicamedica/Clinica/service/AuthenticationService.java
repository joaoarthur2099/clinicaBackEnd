package br.com.clinicamedica.Clinica.service;

import br.com.clinicamedica.Clinica.TokenProvider.TokenProvider;
import br.com.clinicamedica.Clinica.dto.AuthRequestDTO;
import br.com.clinicamedica.Clinica.dto.AuthResponseDTO;
import br.com.clinicamedica.Clinica.dto.RegisterDTO;
import br.com.clinicamedica.Clinica.exception.DuplicatedUserNameException;
import br.com.clinicamedica.Clinica.model.RoleModel;
import br.com.clinicamedica.Clinica.model.UserModel;
import br.com.clinicamedica.Clinica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;

    public AuthResponseDTO login(AuthRequestDTO authRequest) {
        var userNamePasswd = new UsernamePasswordAuthenticationToken(authRequest.userName(), authRequest.password());
        var auth = authenticationManager.authenticate(userNamePasswd);
        var token = tokenProvider.getToken((UserModel) auth.getPrincipal());
        return new AuthResponseDTO(authRequest.userName(), token);
    }

    public void register(RegisterDTO registerData) {
        var found = userDetailsService.loadUserByUsername(registerData.userName());
        if (found != null) {
            throw new DuplicatedUserNameException("Nome de usuário indisponível!");
        } else {
            String encPasswd = new BCryptPasswordEncoder().encode(registerData.password());
            UserModel userModel = new UserModel();
            userModel.setFullName(registerData.fullName());
            userModel.setUserName(registerData.userName());
            userModel.setPassword(encPasswd);
            userModel.setEnabled(true);
            userModel.setRole(RoleModel.USER);
            userModel.setAccountNonLocked(true);
            userModel.setCredentialsNonExpired(true);
            userModel.setAccountNonExpired(true);
            userRepository.save(userModel);
        }
    }
}