package himedia.spring.ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.spring.ver2.model.Member;

/*
 * - 인터페이스 생성 후, JpaRepository<Entity 클래스, PK 타입>을 상속하면,
 * 	 기본적인 CRUD 메서드가 자동으로 생성
 * - @Repository 설정 필요 없음
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
	// <T, ID> T: entity 객체 ID: 기본키의 타입
	
	Member findByUsername(String username); //findById는 기본적으로 있음, username으로 찾는 건 JPA에 없어서 생성
	
}
