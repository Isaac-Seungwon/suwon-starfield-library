package com.test.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JpaApplication은 Spring Boot 애플리케이션의 시작점입니다.
 * Spring Boot 애플리케이션을 실행하는데 사용됩니다.
 * 애플리케이션을 실행할 때 Shutdown Hook을 추가하여 종료 메시지를 출력합니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@SpringBootApplication
public class JpaApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
        
        // Shutdown Hook 추가
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("애플리케이션이 종료됩니다.");
		}));
	}
    
}
