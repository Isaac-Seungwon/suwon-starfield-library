package com.test.jpa.dto;

import lombok.Data;

/**
 * 작가 데이터 전송 객체(DTO)는 작가의 정보를 전달하는 데 사용됩니다.
 * 작가는 고유한 ID와 이름을 가지고 있습니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Data
public class AuthorDTO {

    private Long id; // 작가의 ID
    private String name; // 작가의 이름

}
