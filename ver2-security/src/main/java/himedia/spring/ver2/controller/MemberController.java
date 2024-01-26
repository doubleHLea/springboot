package himedia.spring.ver2.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import himedia.spring.ver2.dto.MemberDTO;
import himedia.spring.ver2.model.Member;
import himedia.spring.ver2.repository.MemberRepository;
import himedia.spring.ver2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final ModelMapper modelMapper;
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/join/admin")
	public String joinAdmin() {
		return "join-admin";
	}
	
	// USER, ADMIN 모두 받을거라 {role}로 받아줌
	@PostMapping("/join/{role}")
	public String createMember(@ModelAttribute MemberDTO memberDTO, Model model) {
		
		// DTO to Entity (이 부분은 메소드로 빼주는게 좋음)
		Member entity = modelMapper.map(memberDTO, Member.class);
		// mapping할 DTO, Entity
		
		//save (원래 service나 repository에서 저장해야 함)
//		Member savedEntity = memberRepository.save(entity);
		// password encoding한 값으로 넣어줌
		Member savedEntity = memberService.joinMember(entity);
		
		// Entity to DTO
		MemberDTO savedDTO = modelMapper.map(savedEntity, MemberDTO.class);
		
		model.addAttribute("member", savedDTO);
		
		return "join-success";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}
