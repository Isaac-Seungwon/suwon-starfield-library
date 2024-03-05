package com.test.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jpa.dto.AuthorDTO;
import com.test.jpa.dto.BookDTO;
import com.test.jpa.model.Author;
import com.test.jpa.model.Book;
import com.test.jpa.service.BookService;

/**
 * 책 컨트롤러(BookController)는 책에 관련된 HTTP 요청을 처리하는 엔드포인트를 제공합니다.
 * 책을 조회하고, 생성하고, 수정하고, 삭제하는 기능을 제공합니다.
 * 책은 고유한 ID를 가지며, 제목, 저자, 가격 등의 속성을 포함합니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * 모든 책을 조회합니다.
	 * 
	 * @return 조회된 모든 책 목록과 함께 HTTP 상태 코드 200을 응답합니다.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

	/**
	 * 특정 ID를 가진 책을 조회합니다.
	 * 
	 * @param id 조회할 책의 ID
	 * @return 조회된 책과 함께 HTTP 상태 코드 200을 응답합니다.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = bookService.getBookById(id);
		return ResponseEntity.ok(book);
	}

	/**
	 * 새로운 책을 생성합니다.
	 * 
	 * @param bookDTO 생성할 책의 정보를 담은 DTO 객체
	 * @return 생성된 책과 함께 HTTP 상태 코드 201을 응답합니다.
	 */
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
		Book book = bookService.createBook(bookDTO.getTitle(), bookDTO.getAuthor().getName(), bookDTO.getPrice());
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}

	/**
	 * 특정 ID를 가진 책을 수정합니다.
	 * 
	 * @param id      수정할 책의 ID
	 * @param bookDTO 수정할 책의 정보를 담은 DTO 객체
	 * @return 수정된 책과 함께 HTTP 상태 코드 200을 응답합니다.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		Book existingBook = bookService.getBookById(id);
		
		// 기존 작가 정보 사용
		Author author = existingBook.getAuthor();
		
		// 책 정보 업데이트
		existingBook.setTitle(bookDTO.getTitle());
		existingBook.setPrice(bookDTO.getPrice());

		// 작가 정보 업데이트
		AuthorDTO authorDTO = bookDTO.getAuthor();
		author.setName(authorDTO.getName());

		Book updatedBook = bookService.updateBook(existingBook);
		return ResponseEntity.ok(updatedBook);
	}

	/**
	 * 특정 ID를 가진 책을 삭제합니다.
	 * 
	 * @param id 삭제할 책의 ID
	 * @return HTTP 상태 코드 204를 응답합니다.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}

}
