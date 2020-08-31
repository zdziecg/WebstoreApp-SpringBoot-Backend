package info.zdziech.webstore.service;


import info.zdziech.webstore.model.Token;
import info.zdziech.webstore.repository.TokenRepository;
import info.zdziech.webstore.repository.UserRepository;
import info.zdziech.webstore.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    private TokenRepository tokenRepository;
    private final TemplateEngine templateEngine;

    private MailService mailService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    //Przykład z video
    public void saveUser(User user){
        userRepository.save(user);
        sendToken(user);
    }
    //Przyklad z video
    public User fetchUserByEmailName(String userMail){
        return userRepository.findByUserMail(userMail);
    }

    public boolean checkUsername(String userName)
    {
        return userRepository.findByUsername(userName).isPresent();
    }

    public boolean checkUserMail(String userMail)
    {
        return userRepository.findByUserMail(userMail) != null;
    }

    public User fetchUserByNameAndPassword(String userName, String password){
        return userRepository.findByUsernameAndPassword(userName, password);
    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, TemplateEngine templateEngine, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.templateEngine = templateEngine;
        this.mailService = mailService;
    }

    public Optional<User> findOne(String username) {
        return userRepository.findByUsername(username);
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
        Context context = new Context();
        context.setVariable("header", "Thank you for registration " + user.getUsername());
        context.setVariable("title", "\n" + "Click on the link below to confirm your registration");
        context.setVariable("description", "http://localhost:7776/token?value=" + tokenValue);
        String body = templateEngine.process("template", context);
        try {
            mailService.sendMail(user.getUserMail(), "Confirm registration!", body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
