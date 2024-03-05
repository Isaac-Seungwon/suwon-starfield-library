package com.test.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jpa.model.Author;
import com.test.jpa.service.AuthorService;

/**
 * 작가 컨트롤러(AuthorController)는 작가에 관련된 HTTP 요청을 처리하는 엔드포인트를 제공합니다.
 * 작가를 생성하고, 작가 이름으로 작가를 조회하는 기능을 제공합니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {
	
    @Autowired
    private AuthorService authorService;

    /**
     * POST 메서드를 통해 새로운 작가를 생성합니다.
     * 요청 본문에는 작가의 정보가 JSON 형식으로 포함되어야 합니다.
     * 
     * @param author 생성할 작가의 정보
     * @return 생성된 작가와 함께 HTTP 상태 코드 201을 응답합니다.
     */
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    /**
     * GET 메서드를 통해 작가 이름으로 작가를 조회합니다.
     * 작가 이름은 URL 경로 변수로 전달되며, 경로에 포함된 이름을 기반으로 작가를 검색합니다.
     * 
     * @param name 조회할 작가의 이름
     * @return 조회된 작가와 함께 HTTP 상태 코드 200을 응답합니다.
     */
    @GetMapping("/{name}")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name) {
        Author author = authorService.getAuthorByName(name);
        return ResponseEntity.ok(author);
    }
    
}
