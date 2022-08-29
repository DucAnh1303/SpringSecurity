package com.example.security.controller;

import com.example.security.entities.Car;
import com.example.security.entities.RegisterUser;
import com.example.security.entities.basreponse.BaseReponse;
import com.example.security.jwt.JwtTokenProvider;
import com.example.security.payload.LoginRequest;
import com.example.security.payload.LoginResponse;
import com.example.security.payload.RandomStuff;
import com.example.security.service.CarService;
import com.example.security.service.CustomUserDetails;
import com.example.security.service.RegisterService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BaseController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public BaseReponse registerUser(@RequestBody RegisterUser registerUser){
        return registerService.registerUser(registerUser);
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);

    }


    // Api /api/random yêu cầu phải xác thực mới có thể request
    @PostMapping("/car-list")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<Car> getByAllCar() {
        return carService.getByAll();
    }
}
