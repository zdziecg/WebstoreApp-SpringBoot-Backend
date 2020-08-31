package info.zdziech.webstore.controller;

import info.zdziech.webstore.model.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping()
public class BasicAuthController {

	@GetMapping(path = "/basicauth")
	public Authentication helloWorldBean() {
		return new Authentication("You are authenticated");
	}	
}
