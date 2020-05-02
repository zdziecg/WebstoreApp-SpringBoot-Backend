package info.zdziech.webstore.Controller;

import info.zdziech.webstore.Model.Token;
import info.zdziech.webstore.Repository.TokenRepository;
import info.zdziech.webstore.Repository.UserRepository;
import info.zdziech.webstore.Service.UserService;
import info.zdziech.webstore.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;

@Controller
    public class UserController {

    private UserService userService;
    private UserRepository userRepository;


    private TokenRepository tokenRepository;

    public UserController(UserService userService, UserRepository userRepository, TokenRepository tokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("user", new User() );
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "hello";
    }


    @GetMapping("/sign-up")
        public String forUser (Model model) {
            model.addAttribute("user", new User());
            return "sign-up";
        }

    @PostMapping("/register")
    public String register (User user) {
        userService.addUser(user);
        return "sign-up";
    }

    @GetMapping("/login")
    public String login () {
        return "login";
    }
    @PostMapping("/login")
    public String login (User user) {
        userService.addUser(user);
        return "login";
    }
    @GetMapping("/token")
    public String signup (@RequestParam String value) {
        Token byValue = tokenRepository.findByValue(value);
        User user = byValue.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return "hello";
    }
}
