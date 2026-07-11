package com.Ram.backend.config;

import com.Ram.backend.user.entity.Role;
import com.Ram.backend.user.entity.User;
import com.Ram.backend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataSeeder {

    @Bean
    CommandLineRunner seedDemoUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            userRepository.save(User.builder()
                    .fullName("Aarav Sharma")
                    .email("aarav.sharma@hostelerp.local")
                    .phoneNumber("9999999999")
                    .password(passwordEncoder.encode("demo-password"))
                    .role(Role.RESIDENT)
                    .build());

            userRepository.save(User.builder()
                    .fullName("Meera Patel")
                    .email("meera.patel@hostelerp.local")
                    .phoneNumber("8888888888")
                    .password(passwordEncoder.encode("demo-password"))
                    .role(Role.RESIDENT)
                    .build());

            userRepository.save(User.builder()
                    .fullName("System Admin")
                    .email("admin@hostelerp.local")
                    .phoneNumber("0000000000")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build());
        };
    }
}