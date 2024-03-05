package com.test.jpa.dto;

import lombok.Data;

/**
 * 책 데이터 전송 객체(DTO)는 책의 정보를 전달하는 데 사용됩니다.
 * 각 책은 고유한 ID, 제목, 작가 정보, 가격을 가지고 있습니다.
 * 작가 정보는 작가 데이터 전송 객체(AuthorDTO)를 사용하여 표현됩니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Data
public class BookDTO {

    private Long id; // 책의 ID
    private String title; // 책의 제목
    private AuthorDTO author; // 책의 작가 정보
    private String price; // 책의 가격

}
