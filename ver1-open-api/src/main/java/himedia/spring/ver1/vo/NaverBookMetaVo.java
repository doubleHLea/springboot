package himedia.spring.ver1.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * [VO] Value Object
 * 값 자체를 표현하는 객체
 * setter 없음 -> 변경(수정) 불가능
 * constructor를 통해서 초기값 설정 후 변경 불가능 -> 리터럴 개념
 * 
 * 읽기만 가능한 read-only 속성을 가진 객체
 * 
 * 값 비교를 위해, equals()와 hashCode() 메서드 오버라이딩
 */

@ToString  				// ToString을 했기때문에 NeverBookMetaVo 요청시 주소가 아닌 string이 나옴
@NoArgsConstructor
@AllArgsConstructor		// 모든 값 생성자 (VO라서 setter는 생성 안함)
@Getter
@EqualsAndHashCode      // VO는 필수적으로 equlas와 hashCode를 오버라이딩해야 함, 롬복에 메소드 있음 
public class NaverBookMetaVo {
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	@ToString.Exclude
	private List<NaverBookVo> items;
}
