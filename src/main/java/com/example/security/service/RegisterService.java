package com.example.security.service;

import com.example.security.entities.RegisterUser;
import com.example.security.entities.Role;
import com.example.security.entities.User;
import com.example.security.entities.basreponse.BaseReponse;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RegisterService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public BaseReponse registerUser(RegisterUser registerUser) {
        List<User> userList = userRepository.getUserName(registerUser.getUserName());
        if (!userList.isEmpty()) {
            return new BaseReponse("userName exits!!", null);
        } else {
            User user = new User();
            user.setUsername(registerUser.getUserName());
            String passwordEncode = passwordEncoder.encode(registerUser.getPassword());
            user.setPassword(passwordEncode);
            Role role=roleRepository.getByRoleName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user = userRepository.save(user);
            return new BaseReponse("register success!!", user);
        }

    }
}
