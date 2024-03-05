package com.test.jpa.service;

import com.test.jpa.model.Author;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * AuthorListener 클래스는 작가 엔티티의 저장 및 업데이트 이벤트를 처리합니다.
 * 작가가 저장되거나 업데이트될 때, 해당 작가의 책 목록에 있는 각 책의 작가를 자신으로 설정합니다.
 * JPA 엔티티 라이프사이클 이벤트를 처리하는데 사용됩니다.
 * 
 * @author 이승원
 * @since 2024.03.05.
 */
public class AuthorListener {

	/**
	 * 작가가 저장되거나 업데이트되기 전에 실행되는 메서드입니다.
	 * 작가의 책 목록에 있는 각 책의 작가를 자신으로 설정합니다.
	 * 
	 * @param author 저장 또는 업데이트될 작가 엔티티
	 */
	@PrePersist
	@PreUpdate
	public void beforeSaveOrUpdate(Author author) {
		author.getBooks().forEach(book -> book.setAuthor(author));
	}

}
