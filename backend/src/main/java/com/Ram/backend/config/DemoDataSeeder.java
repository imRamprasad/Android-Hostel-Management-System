package com.Ram.backend.config;

import com.Ram.backend.user.entity.User;
import com.Ram.backend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataSeeder {

    @Bean
    CommandLineRunner seedDemoUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            userRepository.save(User.builder()
                    .fullName("Aarav Sharma")
                    .email("aarav.sharma@hostelerp.local")
                    .phoneNumber("9999999999")
                    .password("demo-password")
                    .build());

            userRepository.save(User.builder()
                    .fullName("Meera Patel")
                    .email("meera.patel@hostelerp.local")
                    .phoneNumber("8888888888")
                    .password("demo-password")
                    .build());
        };
    }
}