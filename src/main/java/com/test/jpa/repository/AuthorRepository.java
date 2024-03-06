package com.test.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.jpa.model.Author;

/**
 * AuthorRepository 인터페이스는 작가 엔티티에 대한 데이터 액세스 기능을 제공합니다.
 * Spring Data JPA의 JpaRepository를 확장하여 작가 엔티티에 대한 CRUD 기능을 지원합니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
    Author findByName(String name); // 작가의 이름으로 작가를 조회하는 메서드
    
}