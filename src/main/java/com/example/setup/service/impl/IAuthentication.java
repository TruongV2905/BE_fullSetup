package com.example.setup.service.impl;

import com.example.setup.model.request.LoginRequest;
import com.example.setup.model.request.RegisterRequest;
import com.example.setup.model.response.AccountResponse;
import org.springframework.context.annotation.Bean;

public interface IAuthentication {
    @Bean
    public AccountResponse register(RegisterRequest registerRequest);
    @Bean
    public AccountResponse login(LoginRequest loginRequest);
}
