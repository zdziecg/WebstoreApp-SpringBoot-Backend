package info.zdziech.webstore.Service;


import info.zdziech.webstore.Model.Token;
import info.zdziech.webstore.Repository.TokenRepository;
import info.zdziech.webstore.Repository.UserRepository;
import info.zdziech.webstore.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {


    private TokenRepository tokenRepository;

    private MailService mailService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,  TokenRepository tokenRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CLIENT");
        userRepository.save(user);
        sendToken(user);
    }

    private void sendToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);
        tokenRepository.save(token);
        String url = "http://localhost:7776/token?value=" + tokenValue;

        try {
            mailService.sendMail(user.getUserMail(), "Kliknij oby potwierz=dzić rejestrację! ", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
