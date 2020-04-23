package info.zdziech.webstore;

import info.zdziech.webstore.Repository.UserRepository;
import info.zdziech.webstore.Model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    private UserRepository userRepository;


    public Start (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;


        User user = new User();
        user.setUsername("Gre");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setUserMail("kuternoga.kamil@gmail.com");
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);

        User usertwo = new User();
        usertwo.setUsername("Greg");
        usertwo.setPassword(passwordEncoder.encode("Pass"));
        usertwo.setUserMail("zdziech.kamil@gmail.com");
        usertwo.setRole("ROLE_CLIENT");
        usertwo.setEnabled(true);

        userRepository.save(user);
        userRepository.save(usertwo);

    }
}
