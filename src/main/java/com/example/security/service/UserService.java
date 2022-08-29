package com.example.security.service;



import com.example.security.entities.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserByUserId(int id){
        User user = userRepository.findAllById(id);
        if (user == null) {
            throw new UsernameNotFoundException("exception");
        }
        return new CustomUserDetails(user);
    }
}
