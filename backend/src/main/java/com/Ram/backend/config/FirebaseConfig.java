package com.Ram.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);
    private static final String SERVICE_ACCOUNT_PATH = "src/main/resources/firebase-service-account.json";

    @Bean
    public FirebaseApp firebaseApp() {
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();
        }

        File file = new File(SERVICE_ACCOUNT_PATH);
        if (!file.exists()) {
            logger.warn("Firebase service account file not found at {}. Social login and OTP will be disabled.", SERVICE_ACCOUNT_PATH);
            return null;
        }

        try (FileInputStream serviceAccount = new FileInputStream(file)) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            logger.error("Failed to initialize FirebaseApp: {}", e.getMessage());
            return null;
        }
    }

    @Bean
    public FirebaseAuth firebaseAuth(Optional<FirebaseApp> firebaseApp) {
        return firebaseApp.map(FirebaseAuth::getInstance).orElse(null);
    }
}
