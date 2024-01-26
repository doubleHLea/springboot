package himedia.spring.ver2.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter  // Entity에서는 setter 사용을 권장하지 않음
@NoArgsConstructor(access = AccessLevel.PRIVATE)	// Entity는 JPA에서는 꼭 매개변수가 없는 생성자가 있어야 동작함
// entity 생성자는 기본적으로 권한을 private으로 설정
//@Table(name = ) 테이블명이 다르면 어노테이션으로 매핑 가능 
/*
 *  @Builder 
 *  기본적으로 모든 필드를 가지고 생성자를 대체해서 쓸 수 있게 해줌 
 *  일반 생성자는 타입만 맞으면 값이 들어가지만 builder는 이름도 똑같아야하고 순서도 보장해줌
 *	default값을 정해서특정 필드를 제외할 수도 있음
 */
public class Member {
//	Entity를 다른 계층에서 DTO처럼 사용하는 건 좋지 않음!
//  그래서 Entity에는 Setter 사용 비권장
	
	@Id	// 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동증가 
	private Long id;
	
	@Column(name = "username", unique = true) // 안넣어줘도 이름이 같으면 자동 매핑, unique 설정
	// @Column(name = "user_name") DB에서는 user_name 언더바를 많이 써서 java에서는 빼서 설정해주는게 좋음
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		// password를 저장할 때 암호화기법을 붙여서 저장, 아니면 에러남
//		this.password = "{noop}" + this.password;
		this.password = passwordEncoder.encode(this.password);
	}
}
