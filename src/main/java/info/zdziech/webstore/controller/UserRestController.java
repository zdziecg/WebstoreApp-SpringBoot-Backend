package info.zdziech.webstore.controller;


import info.zdziech.webstore.model.Product;
import info.zdziech.webstore.model.Token;
import info.zdziech.webstore.model.User;
import info.zdziech.webstore.repository.TokenRepository;
import info.zdziech.webstore.repository.UserRepository;
import info.zdziech.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins="https://zdziecg-webstore.herokuapp.com")
public class UserRestController {


    private UserService userService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private HttpServletRequest request;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(UserService userService, TokenRepository tokenRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        String tempUserName = user.getUsername();
        String tempPassword = user.getPassword();
        return userService.fetchUserByNameAndPassword(tempUserName, tempPassword).equals(user);
    }
    @PostMapping("/registeruser")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmailName = user.getUserMail();
        if (tempEmailName != null && !"".equals(tempEmailName)){
           User userObj =  userService.fetchUserByEmailName(tempEmailName);
           if (userObj != null){
               throw new Exception("User with thin email is exist");
           }

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_GUEST");
        userService.saveUser(user);
        return user;
    }
    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @PostMapping("/loginuser")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempUserName = user.getUsername();
        String tempPassword = passwordEncoder.encode(user.getPassword());
        User userObj = null;
        if (tempUserName != null && tempPassword!= null){
           userObj =  userService.fetchUserByNameAndPassword(tempUserName, tempPassword);
        }
        if (userObj == null){
            throw new Exception("Bad username or password");
        }
        else if(!user.isEnabled()){
            throw new Exception("Confirm registration ");
        }
        return userObj;
    }
    @GetMapping("/checkUserName")
    public boolean checkUsername(String username){
        return userService.checkUsername(username);
    }


    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
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

//    @GetMapping("/users")
//        public List<User> getUsers() {
//            return (List<User>) userRepository.findAll();
//        }
//
//        @PostMapping("/users")
//        void addUser(@RequestBody User user) {
//            userRepository.save(user);
//        }

//        @GetMapping("/token")
//    public User checkUser (@RequestParam String value) {
//        Token byValue = tokenRepository.findByValue(value);
//        User user = byValue.getUser();
//        user.setEnabled(true);
//        userRepository.save(user);
//
//        return user;
//    }

        @GetMapping("/token")
        public String signup (@RequestParam String value) {
        Token byValue = tokenRepository.findByValue(value);
        User user = byValue.getUser();
        user.setEnabled(true);
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return "Poprawna rejestracja ";
    }


//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
//        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
//    }
}


