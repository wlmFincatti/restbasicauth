package br.com.wfincatti.basicauth;


import br.com.wfincatti.basicauth.configuration.BCryptorEncoderConfig;
import br.com.wfincatti.basicauth.domain.entity.Role;
import br.com.wfincatti.basicauth.domain.entity.User;
import br.com.wfincatti.basicauth.domain.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitDB implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptorEncoderConfig bCryptorEncoderConfig;

    @Override
    public void run(String... args) throws Exception {
        User userAdmin = new User();
        userAdmin.setPassword(bCryptorEncoderConfig.passwordEncoder().encode("admin"));
        userAdmin.setUsername("admin");
        userAdmin.grantAuthority(Role.ROLE_ADMIN);

        User user = new User();
        user.setPassword(bCryptorEncoderConfig.passwordEncoder().encode("user"));
        user.setUsername("user");
        user.grantAuthority(Role.ROLE_USER);

        userRepository.saveAll(Arrays.asList(user, userAdmin));
    }
}
