package himedia.spring.ver1.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import himedia.spring.ver1.vo.NaverBookMetaVo;
import himedia.spring.ver1.vo.NaverBookVo;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	
	@GetMapping("/book/list")
	public String list(@RequestParam(value="query") String query, Model model) {
		log.info("query : {}", query);
		
		// [네이버 검색 API 요청] =================================
		String clientId = "ifUY7WDM0CZMJEQ8rWkD";
		String clientSecret = "WADFfgfExc";
		
		log.info("1. uri 생성");
		URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
				.path("/v1/search/book")
				.queryParam("query", query)
				.queryParam("display", 10)
				.queryParam("start", 1)
				.queryParam("sort", "sim")
				.encode()
				.build()
				.toUri();
		
		/*
		 * [Spring Framework 제공] HttpEntity class ======================================
		 * : HTTP 요청(request) 또는 응답(response)에 해당하는 HttpHeader와 HttpBody 포함
		 * : 자식 클래스 - RequestEntity, ResponseEntity
		 * 
		 * : RequestEntity(요청 준비) -> RestTemplate(요청) -> ResponseEntity(응답)
		 * -------------------------------------------------------------------------------
		 * 							request(요청) 				response(응답)
		 */
		
		log.info("2. request 요청 준비");
		RequestEntity<Void> requestEntity = RequestEntity
				.get(uri)
				.header("X-Naver-Client-Id", clientId)
				.header("X-Naver-Client-Secret", clientSecret)
				.build();
		
		log.info("3. restTemplate 객체 생성");
		RestTemplate restTemplate = new RestTemplate();
		
		log.info("4. restTemplate.exchange() 요청");
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		log.info("responseEntity : {}", responseEntity);
		log.info("responseEntity.getBody : {}", responseEntity.getBody());
		
		// JSON 파싱 (JSON 문자열을 객체로 변환)
		log.info("5. json -> 객체 변환");
		ObjectMapper objectMapper = new ObjectMapper();
		NaverBookMetaVo naverBookMetaVo = null;
		
		try {
			naverBookMetaVo = objectMapper.readValue(responseEntity.getBody(), NaverBookMetaVo.class);
			// json, 리턴할 클래스 : json에 있는 키와 클래스를 매핑해줌
			log.info("naeverBookMetaVo : {}", naverBookMetaVo);
			log.info("naverBookMetaVo.getTotal : {}", naverBookMetaVo.getTotal());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//
		List<NaverBookVo> books = naverBookMetaVo.getItems();
		
		// view에 넘기기
		model.addAttribute("books", books);
		
		return "book/list";
	}
	
}
