package com.test.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MainController는 Spring MVC에서 웹 요청을 처리하는 컨트롤러입니다.
 * 웹 애플리케이션의 메인 페이지를 제공하기 위해 "/" 경로에 대한 GET 요청을 처리합니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Controller
public class MainController {

    /**
     * 웹 애플리케이션의 메인 페이지를 반환합니다.
     * 
     * @return "index" 문자열
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}
