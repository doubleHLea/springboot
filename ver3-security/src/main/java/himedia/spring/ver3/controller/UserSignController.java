package himedia.spring.ver3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignController {
	
	@GetMapping("/signIn")
	public String signIn() {
		return "sign/signIn";
	}
	@GetMapping("/signOut")
	public String signOut() {
		return "sign/signOut";
	}
	
	@GetMapping("/sign/success")
	public String success() {
		return "sign/success";
	}
	
}
