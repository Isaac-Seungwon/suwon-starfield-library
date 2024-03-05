package com.test.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

/**
 * 제품 엔티티(Product)는 책과 같은 제품들의 공통 정보를 나타내는데 사용됩니다.
 * 각 제품은 고유한 ID와 이름을 가지고 있습니다.
 * 추상 클래스로 정의되어 있으며, 제품에 따라 구체적인 엔티티가 상속받아 사용됩니다.
 * 제품의 ID는 TABLE 전략을 사용하여 자동으로 생성되도록 설정되었습니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
	
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id; // 제품의 ID

    private String name; // 제품의 이름
    
}
