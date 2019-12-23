package com.ens.service.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FCMInitializer {

    @Value("${app.firebase.configuration.file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() {
        try {

            log.info("### Reading firebase configuration file from : {}",firebaseConfigPath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileSystemResource(firebaseConfigPath).getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("### Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error("{}",e.getMessage());
        }
    }

}
