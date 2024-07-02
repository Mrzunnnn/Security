package com.example.security.security;

import com.example.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private List<User> users = new ArrayList<>();

    public CustomUserDetailService(PasswordEncoder passwordEncoder) {
        users.add(new User(1, "dung@gmail.com", "Dũng", passwordEncoder.encode("123"), List.of("USER", "ADMIN")));
        users.add(new User(2, "hoang@gmail.com", "Hoàng", passwordEncoder.encode("123"), List.of("USER")));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy email phù hợp"));
    }
}
