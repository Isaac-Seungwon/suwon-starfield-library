package com.test.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 책 엔티티(Book)는 제품(Product)을 상속받아 책의 정보를 나타내는데 사용됩니다.
 * 각 책은 고유한 ID, 제목, 작가 정보, 가격을 가지고 있습니다.
 * 책의 작가 정보는 다대일 관계로 표현되며, 책이 삭제되어도 작가 정보는 유지됩니다.
 * 책의 ID는 IDENTITY 전략을 사용하여 자동으로 생성되도록 설정되었습니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Book extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 책의 ID

    private String title; // 책의 제목

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("books") // 무한루프 방지
    private Author author; // 책의 작가 정보

    private String price; // 책의 가격
}
