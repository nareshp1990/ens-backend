package com.ens.api;

import com.ens.domain.payload.user.UserResponse;
import com.ens.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/auth")
public class AuthApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestParam String mobileNumber, @RequestParam String password){
        return ResponseEntity.ok(userService.login(mobileNumber,password));
    }

}
