import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.jpa.model.Author;
import com.test.jpa.model.Book;
import com.test.jpa.repository.AuthorRepository;
import com.test.jpa.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        // 가짜 책 데이터 생성
        List<Book> fakeBooks = new ArrayList<>();
        fakeBooks.add(new Book(1L, "테스트 책 1", new Author(1L, "테스트 작가 1"), "10000"));
        fakeBooks.add(new Book(2L, "테스트 책 2", new Author(2L, "테스트 작가 2"), "20000"));
        fakeBooks.add(new Book(3L, "테스트 책 3", new Author(3L, "테스트 작가 3"), "30000"));

        // Mock 객체 설정
        when(bookRepository.findAll()).thenReturn(fakeBooks);

        // 테스트 실행
        List<Book> result = bookService.getAllBooks();

        // 결과 검증
        assertEquals(3, result.size());
    }

    @Test
    public void testCreateBook() {
        // 가짜 작가 데이터 생성
        Author fakeAuthor = new Author(1L, "테스트 작가");

        // 가짜 책 데이터 생성
        Book fakeBook = new Book(1L, "테스트 책", fakeAuthor, "10000");

        // Mock 객체 설정
        when(authorRepository.findByName("테스트 작가")).thenReturn(fakeAuthor);
        when(bookRepository.save(fakeBook)).thenReturn(fakeBook);

        // 테스트 실행
        Book result = bookService.createBook("테스트 책", "테스트 작가", "10000");

        // 결과 검증
        assertEquals("테스트 책", result.getTitle());
        assertEquals("테스트 작가", result.getAuthor().getName());
        assertEquals("10000", result.getPrice());
    }
}
