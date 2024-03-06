package com.test.jpa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.jpa.domain.BookDTO;
import com.test.jpa.mapper.BookMapper;
import com.test.jpa.model.Author;
import com.test.jpa.model.Book;
import com.test.jpa.repository.AuthorRepository;
import com.test.jpa.repository.BookRepository;

/**
 * BookService 클래스는 책 관련 비즈니스 로직을 처리합니다.
 * 책 엔티티의 생성, 조회, 수정, 삭제 기능을 제공합니다.
 * 작가의 이름을 이용하여 책을 생성할 수 있습니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;
    
    @Autowired
    public BookService(BookMapper bookMapper, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * ID를 기반으로 책을 조회하는 메서드입니다.
     * 
     * @param id 조회할 책의 ID
     * @return 해당 ID를 가진 책 엔티티
     * @throws RuntimeException 책을 찾지 못한 경우 예외 발생
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    /**
     * 모든 책을 조회하는 메서드입니다.
     * 
     * @return 모든 책 엔티티의 목록
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * 새로운 책을 생성하는 메서드입니다.
     * 
     * @param title     책의 제목
     * @param authorName 작가의 이름
     * @param price     책의 가격
     * @return 생성된 책 엔티티
     */
    @Transactional
    public Book createBook(String title, String authorName, String price) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            author = authorRepository.save(author);
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);

        return bookRepository.save(book);
    }

    /**
     * 책 정보를 업데이트하는 메서드입니다.
     * 
     * @param updatedBook 업데이트할 책 정보
     * @return 업데이트된 책 엔티티
     * @throws RuntimeException 책 업데이트 실패 시 예외 발생
     */
    @Transactional
    public Book updateBook(Book updatedBook) {
        try {
            Long id = updatedBook.getId();
            Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPrice(updatedBook.getPrice());

            return bookRepository.save(existingBook);
        } catch (RuntimeException ex) {
            System.out.println("Book update failed: " + ex.getMessage());
            throw new RuntimeException("Book update failed: " + ex.getMessage());
        }
    }

    /**
     * 책을 삭제하는 메서드입니다.
     * 
     * @param id 삭제할 책의 ID
     */
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
    
    /**
     * 전체 책 개수를 반환하는 메서드입니다.
     * 
     * @param map 책 개수 조회에 필요한 파라미터를 담은 맵
     * @return 전체 책 개수
     */
    public int getTotalCount() {
        return bookMapper.getTotalCount();
    }

    /**
     * 책의 아이디를 기반으로 책을 조회하는 메서드입니다.
     * 
     * @param id 조회할 책의 아이디
     * @return 조회된 책 엔티티의 Optional 객체
     */
    public Optional<BookDTO> findByBookId(long id) {
        return bookMapper.findByBookId(id);
    }

}
