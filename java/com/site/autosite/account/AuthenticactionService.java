package com.site.autosite.account;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticactionService {

    private final AcccountRepository acccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticactionService(AcccountRepository acccountRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager) {
        this.acccountRepository = acccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Account request){
        Account account = new Account();
        account.setLogin(request.getLogin());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(request.getRole());

        account = acccountRepository.save(account);

        String token = jwtService.generateToken(account);

        return new AuthenticationResponse(token);
    }

    public  AuthenticationResponse authenticate(Account request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
            )
        );

        Account account = acccountRepository.findByLogin(request.getLogin()).orElseThrow();

        String token = jwtService.generateToken(account);

        return new AuthenticationResponse(token);
    }
}
