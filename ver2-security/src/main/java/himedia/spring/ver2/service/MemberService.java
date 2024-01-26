package himedia.spring.ver2.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import himedia.spring.ver2.model.Member;
import himedia.spring.ver2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

/*
 * UserDetailService
 * : DAO를 사용하여 user 정보 인증에 사용
 * : DB 형태는 상관 없음
 */

@Service
@RequiredArgsConstructor // 자동주입 이렇게 해도 됨
public class MemberService implements UserDetailsService{
	// UserDetailsService (DAO : DB에 접근해서 데이터를 가져오는 역할) 
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private MemberRepository memberRepository;
	
	// 저장이 된 데이터를 가지고 옴(로그인 정보)
	// 저장된 사용자의 정보를 가지고 와서 비교까지 다 해주는 메소드가 있음
	// loadUserByUsername : 저장된 사용자 정보 가져옴
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByUsername(username);
		
		// 사용자가 없으면 (원래 Optional로 처리해주는게 좋음)
		if(member == null) {
			throw new UsernameNotFoundException(username);
		}
		
		// 사용자가 있다면
		// Builder Pattern (생성 패턴) : 생성자를 편하게 쓰기위해 대처할 수 있음
		return User // UserDetails 다형성 구현되어 있어서 User로 리턴
				.builder() // builder()가 생성자를 대체해 줌(매개변수 순서와 상관없이 넣어줄 수 있음)
				.username(member.getUsername())
				.password(member.getPassword())
				.roles(member.getRole())
				.build();
		
	}
	
	// 비밀번호 암호화 : Encoding
	public Member joinMember(Member member) {
		// 이미 저장된 값을 다시 변경해서 저장
//		member.encodePassword();
		member.encodePassword(passwordEncoder);
		return memberRepository.save(member); 
	}
	
}
