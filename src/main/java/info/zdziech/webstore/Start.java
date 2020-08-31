package info.zdziech.webstore;

import info.zdziech.webstore.repository.CartRepository;
import info.zdziech.webstore.repository.UserRepository;
import info.zdziech.webstore.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    private UserRepository userRepository;
    private CartRepository cartRepository;


    public Start (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;


        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setUserMail("user@gmail.com");
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);

        User usertwo = new User();
        usertwo.setUsername("Test");
        usertwo.setPassword(passwordEncoder.encode("Test"));
        usertwo.setUserMail("test@gmail.com");
        usertwo.setRole("Role_Client");
        usertwo.setEnabled(true);

        User t = new User();
        t.setUsername("1234");
        t.setPassword(passwordEncoder.encode("1234"));
        t.setUserMail("hgfd.kamil@gmail.com");
        t.setRole("Role_Client");
        t.setEnabled(true);


        userRepository.save(user);
        userRepository.save(usertwo);
        userRepository.save(t);

    }
}
