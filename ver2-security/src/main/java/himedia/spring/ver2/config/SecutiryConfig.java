package himedia.spring.ver2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {

	// SecurityFilterChain 제일 중요!!
	@Bean   //리턴이 되는 객체를 bean으로 등록하겠다는 의미, bean이 생성되어야 Security에서 씀
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// SecutiryFilterChain은 객체 리턴, 예외 발생, 매개변수 정해져있음
		
		// 메소드 체이닝
		http
			.csrf((csrf) -> csrf.disable()) // 보안 공격 유형 중에 csrf 공격을 방어하는 메소드 (사용 안하면 disable)
			.authorizeHttpRequests((authorizeRequest) -> 
					authorizeRequest
						.requestMatchers("/", "/info", "/join/**", "/login", "/css/**", "/logout").permitAll() // 인증을 제외할 페이지 설정(요청 uri 넣어줌)
						// 해당 리소스(URI) 접근을 인증 절차 없이 허용
						// static등의 경로도 uri처럼 넣어서 인증할 수 있음
						
						.requestMatchers("/admin").hasRole("ADMIN") // 역할을 정해줌("ADMIN" 정해져있음) admin은 로그인뿐만 아니라 역할까지 지정되어야 사용 가능 
						// /admin 리소스(URI) 접근을 ADMIN role만 허용
						
						.anyRequest().authenticated())  // 나머지 리소스는 인증 절차 필요   
						.exceptionHandling(error -> 
								error.accessDeniedPage("/access/denied") // 접근 거부 페이지 URL
								)
//			.formLogin(Customizer.withDefaults());		// 폼 로그인 사용
			.formLogin(formLogin -> 					// 로그인 뷰 지정(POST 요청)
					formLogin
						.loginPage("/login-form")  // 사용자 정의 로그인 페이지(html)
						// 로그인이 필요한 페이지에 접속하면, 로그인 페이지로 redirect
						// 인증되지 않은 사용자에게 보여줄 페이지 설정
						.defaultSuccessUrl("/", true) 	// 로그인 성공 시, 이동 URL
						// 매개변수가 하나인 메소드는 다른 경우에는 다른 URL 요청 가능
						.failureUrl("/login?error=true")// 로그인 실패 시, 이동 URL
						.usernameParameter("username")	// 아이디 파라미터 설정, default: username
						// 기본값이 username인데 바꾸고 싶을 때 사용
						.passwordParameter("password") 	// 비밀번호 파라미터 설정, default: password
						.loginProcessingUrl("/login")		// 로그인 Form Action URL, default: /login
						// 기본값이 /login이라서 새로 만든 login 요청 uri가 동일할 시 post 매핑 안해줘도 됨
					);
		http.logout(logout -> 
				logout.logoutUrl("/logout")	// 로그아웃 시, 이동 URL
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // logoutUrl("/logout")과 같음
				.logoutSuccessUrl("/") 	// 로그아웃 성공 시, 이동 URL
				.invalidateHttpSession(true)	// 세션 삭제(세션 무효화)
				);
		
		return http.build(); // 설정된 http로 bean을 만들어주는 메소드
	}
	
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("test").password("{noop}1111").roles("USER")
//		// 비밀번호를 평문화(1111 암호화하지 않고 그대로 사용) 하면 안 됨
//		// password("{기법}1111") 암호화하는 다양한 기법이 있음 기법 넣고 비밀번호 넣으면 됨
//		// {noop} 암호화하지 않는 기법
//		.and()
//		.withUser("admin1").password("{noop}1111").roles("ADMIN");
//	}
	
//	// [비밀번호 암호화 Bean 등록] v5이상부터 사용 가능
//	// : DB에 비밀번호를 평문으로 저장하지 않고 Hash를 진행한 뒤 저장
//	// : 단방향(암호화를 하고 다시 되돌릴 수 없음:복화) 해쉬 알고리듬에 salt(해쉬에서 같은 값은 다 똑같아서)를 추가하여 encoding
//	// : 
//	@Bean
//	protected PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		// 원래 객체 생성을 해야하는데 얘가 다 해결해줌
//		// 기본이 String encodingId = "bcrypt";
//	}
	
	
}
