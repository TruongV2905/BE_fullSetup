package com.example.setup.service.impl;

import com.example.setup.model.request.LoginRequest;
import com.example.setup.model.request.RegisterRequest;
import com.example.setup.model.response.AccountResponse;

public interface IAuthentication {

    AccountResponse register(RegisterRequest registerRequest);


    AccountResponse login(LoginRequest loginRequest);
}
