package himedia.spring.ver2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "[controller] index 페이지입니다.");
		return"index";
	}
	
	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "[controller] info 페이지입니다.");
		return "info";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("message", "[controller] dashboard 페이지입니다.");
		return "dashboard";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("message", "[controller] admin 페이지입니다.");
		return "admin";
	}	
	
}
