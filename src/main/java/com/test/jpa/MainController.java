package com.test.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.jpa.service.BookService;

/**
 * MainController는 Spring MVC에서 웹 요청을 처리하는 컨트롤러입니다.
 * 웹 애플리케이션의 메인 페이지를 제공하기 위해 "/" 경로에 대한 GET 요청을 처리합니다.
 * 
 * 작성자: 이승원
 * 작성일: 2024.03.05.
 */
@Controller
public class MainController {
    
    @Autowired
    private BookService bookService;

    /**
     * 웹 애플리케이션의 메인 페이지를 반환합니다.
     * 
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return "index" 문자열
     */
    @GetMapping("/")
    public String index(Model model) {
        // 전체 책 권수 가져오기
        int totalCount = bookService.getTotalCount();
        
        // 모델에 전체 책 권수 추가
        model.addAttribute("totalCount", totalCount);
       
        return "index";
    }
    
}
