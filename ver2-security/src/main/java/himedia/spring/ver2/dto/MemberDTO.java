package himedia.spring.ver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

	private Long id;
	private String username;
	private String password;
	private String role;
	
}
