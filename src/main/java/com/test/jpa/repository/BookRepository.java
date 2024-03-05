package com.test.jpa.repository;

import com.test.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookRepository 인터페이스는 책 엔티티에 대한 데이터 액세스 기능을 제공합니다.
 * Spring Data JPA의 JpaRepository를 확장하여 책 엔티티에 대한 CRUD 기능을 지원합니다.
 * 작가의 이름으로 책을 조회하는 쿼리 메서드를 추가로 정의하였습니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    /**
     * 작가의 이름으로 책을 조회하는 쿼리 메서드입니다.
     * 
     * @param authorName 작가의 이름
     * @return 해당 작가가 작성한 책의 목록
     */
    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
    List<Book> findByAuthorName(@Param("authorName") String authorName);
    
}
