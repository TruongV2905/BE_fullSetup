package com.example.setup.controller;

import com.example.setup.model.request.LoginRequest;
import com.example.setup.model.request.RegisterRequest;
import com.example.setup.model.response.AccountResponse;
import com.example.setup.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class AuthencationAPI {

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AccountResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("login")
    public ResponseEntity<AccountResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AccountResponse newAccount = authenticationService.login(loginRequest);
//        messagingTemplate.convertAndSend("./topic/root", "Create ");
        return ResponseEntity.ok(newAccount);
    }
}
