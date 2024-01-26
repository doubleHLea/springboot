package himedia.spring.ver2.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

	// [비밀번호 암호화 Bean 등록] v5이상부터 사용 가능
	// : DB에 비밀번호를 평문으로 저장하지 않고 Hash를 진행한 뒤 저장
	// : 단방향(암호화를 하고 다시 되돌릴 수 없음:복화) 해쉬 알고리듬에 salt(해쉬에서 같은 값은 다 똑같아서)를 추가하여 encoding
	// : 
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// 원래 객체 생성을 해야하는데 얘가 다 해결해줌
		// 기본이 String encodingId = "bcrypt";
	}
}
