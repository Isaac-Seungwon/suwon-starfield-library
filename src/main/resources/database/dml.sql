-- dml

-- Insert sample data into Author table
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '랑가 라오 카라남');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '오카다 다쿠미');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '박우창');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '채규태');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '홍팍');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '고재성');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '윤인성');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '한정수');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '김담형');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '송형주');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '박상헌');
INSERT INTO Author (id, name) VALUES (author_seq.NEXTVAL, '박규석');

SELECT * FROM Author;

-- Insert sample data into Book table
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '스프링 5 마스터', 40000, 1);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '모던 자바스크립트로 배우는 리액트 입문', 23000, 2);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '오라클로 배우는 데이터베이스 개론과 실습', 29000, 3);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '채쌤의 자바 프로그래밍 핵심',  27000, 4);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '채쌤의 Servlet&JSP 프로그래밍 핵심', 27000, 4);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '코딩 자율학습 스프링 부트 3 자바 백엔드 개발 입문', 33000, 5);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '자바를 부탁해', 26000, 5);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, 'IT 엔지니어를 위한 네트워크 입문', 36000, 6);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, 'HTML5 웹 프로그래밍 입문', 26000, 7);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '객체 지향 설계와 분석을 위한 UML 기초와 응용', 24000, 8);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '서비스 운영이 쉬워지는 AWS 인프라 구축 가이드', 27000, 9);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '인사이드 자바스크립트', 18000, 10);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '기초부터 다지는 ElasticSearch 운영 노하우', 34000, 11);
INSERT INTO Book (id, title, price, author_id) VALUES (book_seq.NEXTVAL, '운영체제', 24000, 12);

SELECT * FROM Book;
SELECT COUNT(*) FROM Book;

COMMIT;
