package himedia.spring.ver3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signIn")
	public String signIn() {
		return "sign/signIn";
	}
	
	@PostMapping("/sign/success")
	public String success() {
		return "sign/success";
	}
	
}
