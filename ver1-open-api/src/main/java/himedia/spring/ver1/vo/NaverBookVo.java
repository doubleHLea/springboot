package himedia.spring.ver1.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class NaverBookVo {
	private String title;
	private String link;
	private String image;
	private String author;
	private Integer discount;
	private String publisher;
	private String isbn;
	private String description;
	private String pubdate;
}
