package info.zdziech.webstore.controller;


import info.zdziech.webstore.model.User;
import info.zdziech.webstore.model.Greeting;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping()
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(@AuthenticationPrincipal User customUser, Model model, HttpServletRequest request,
					   HttpServletResponse response, Locale locale) throws Exception {
		model.addAttribute("name", customUser.getUsername());

		return ("Entering Home controller @AuthenticationPrincipal: " + model);
	}

}
