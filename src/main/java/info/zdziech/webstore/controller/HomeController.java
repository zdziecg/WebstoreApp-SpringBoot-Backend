package info.zdziech.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
    public class HomeController {
        @RequestMapping("/")
        public String welcome( Model model) {
            model.addAttribute("greeting", "Największy wybór produktów!");
            return "index";
        }
    @RequestMapping("/index")
    public String greeting() {
        return "redirect:/";
    }
}

