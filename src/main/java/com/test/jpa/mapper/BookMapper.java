package com.test.jpa.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.test.jpa.domain.BookDTO;

/*
 * Book 엔티티와 관련된 데이터베이스 액세스를 위한 매퍼 인터페이스입니다.
 * 전체 책 권수를 조회하거나, 책의 아이디를 기반으로 책을 조회할 수 있습니다.
 * 
 * 작성자: 이승원
 * 작성일: 2024.03.06.
 */
@Mapper
public interface BookMapper {

    /**
     * 전체 책 권수를 반환하는 메서드입니다.
     * 
     * @return 전체 책 권수
     */
    int getTotalCount();

    /**
     * 책의 아이디를 기반으로 책을 조회하는 메서드입니다.
     * 
     * @param id 조회할 책의 아이디
     * @return 조회된 책 엔티티의 Optional 객체
     */
    @Select("SELECT * FROM Book where id = #{id}")
    public Optional<BookDTO> findByBookId(long id);
    
}
