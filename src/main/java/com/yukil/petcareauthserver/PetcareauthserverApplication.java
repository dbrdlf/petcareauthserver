package com.yukil.petcareauthserver;

import com.yukil.petcareauthserver.entity.AuthType;
import com.yukil.petcareauthserver.entity.AuthUser;
import com.yukil.petcareauthserver.repository.AuthUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class PetcareauthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetcareauthserverApplication.class, args);
    }

    @Component
    class Initializer implements ApplicationRunner{
        @Resource
        private AuthUserRepository authUserRepository;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            AuthUser authUser = new AuthUser();
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            authUser.setUsername("test");
            authUser.setPassword(passwordEncoder.encode("pass"));
            authUser.setAuthType(Set.of(AuthType.USER, AuthType.ADMIN));
            authUser.setLocalDate(LocalDate.now());
            authUserRepository.save(authUser);
        }
    }

}
