package com.example.authenapijwt.controller;

import com.example.authenapijwt.dto.AccountDTO;
import com.example.authenapijwt.dto.RegisterDTO;
import com.example.authenapijwt.entity.Account;
import com.example.authenapijwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register( @RequestBody RegisterDTO registerDTO) {
        AccountDTO account = authenticationService.saveAccount(registerDTO);
        return ResponseEntity.ok().body(account);
    }
}
