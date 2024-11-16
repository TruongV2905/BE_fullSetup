package com.example.setup.service;

import com.example.setup.entity.Account;
import com.example.setup.exception.AuthException;
import com.example.setup.mapper.AccountMapper;
import com.example.setup.model.constant.Role;
import com.example.setup.model.request.EmailDetail;
import com.example.setup.model.request.LoginRequest;
import com.example.setup.model.request.RegisterRequest;
import com.example.setup.model.response.AccountResponse;
import com.example.setup.repository.AccountRepository;
import com.example.setup.service.impl.IAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService implements UserDetailsService, IAuthentication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;


    @Override
    public AccountResponse register(final RegisterRequest registerRequest) {
        // map RegisterRequest => Account
        Account account = accountMapper.toAccount(registerRequest);
        account.setRole(Role.CUSTOMER);


        try {
            String originPassword = account.getPassword();
            account.setPassword(passwordEncoder.encode(originPassword));
            Account newAccount = accountRepository.save(account);
            // gửi mail về cho người dùng
            EmailDetail emailDetail = EmailDetail.builder()
                    .receiver(newAccount)
                    .link("https://github.com/TruongV2905")
                    .subject("Welcome to my home")
                    .build();
            emailService.sendEmail(emailDetail);
            return accountMapper.toAccountResponse(newAccount);
        } catch (Exception e) {

            if (e.getMessage().contains(account.getEmail())) {
                throw new AuthException("Duplicate email!");
            } else {
                throw new AuthException(e.getMessage());
            }
        }
    }

    @Override
    public AccountResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));
            Account account = (Account) authentication.getPrincipal();
            AccountResponse accountResponse = accountMapper.toAccountResponse(account);
            accountResponse.setToken(tokenService.generateToken(account));
            return accountResponse;
        } catch (Exception e) {
            throw new AuthException("Username or password invalid!");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findAccountByEmail(email);
    }
}
