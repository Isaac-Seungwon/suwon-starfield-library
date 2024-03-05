package com.test.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.jpa.model.Author;
import com.test.jpa.repository.AuthorRepository;

import jakarta.transaction.Transactional;

/**
 * AuthorService 클래스는 작가 관련 비즈니스 로직을 처리합니다.
 * 작가 엔티티의 생성과 작가 이름으로 작가를 조회하는 기능을 제공합니다.
 * 작가 엔티티의 CRUD(Create, Read) 작업을 담당합니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;

	/**
	 * 작가를 생성하는 메서드입니다.
	 * 
	 * @param author 저장할 작가 엔티티
	 * @return 저장된 작가 엔티티
	 */
	@Transactional
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	/**
	 * 작가의 이름으로 작가를 조회하는 메서드입니다.
	 * 
	 * @param name 조회할 작가의 이름
	 * @return 해당 이름을 가진 작가 엔티티
	 */
	public Author getAuthorByName(String name) {
		return authorRepository.findByName(name);
	}
	
}