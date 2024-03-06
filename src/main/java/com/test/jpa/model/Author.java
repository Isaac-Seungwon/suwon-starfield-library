package com.test.jpa.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

import com.test.jpa.service.AuthorListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/**
 * 작가 엔티티(Author)는 책의 저자 정보를 나타내는데 사용됩니다.
 * 각 작가는 고유한 ID와 이름을 가지고 있으며, 여러 책을 작성할 수 있습니다.
 * 작가의 책 정보는 일대다 관계로 표현되며, CascadeType.ALL을 사용하여 작가가 삭제될 때 해당 작가의 책도 함께 삭제됩니다.
 * 작가 엔티티는 AuthorListener 클래스를 리스너로 사용하여 업데이트 이벤트를 처리합니다.
 * Hibernate의 @DynamicUpdate 어노테이션을 사용하여 엔티티가 업데이트될 때 동적으로 업데이트 쿼리를 생성합니다.
 * 작가 ID는 시퀀스 생성기를 사용하여 자동으로 생성되며, 초기값은 1이며, allocationSize는 1로 설정됩니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Data
@Entity
@EntityListeners(AuthorListener.class)
@DynamicUpdate
@SequenceGenerator(
    name = "author_seq_generator",
    sequenceName = "author_seq",
    initialValue = 1,
    allocationSize = 1
)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq_generator")
    private Long id; // 작가의 ID

    private String name; // 작가의 이름

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>(); // 작가의 책 목록
}
