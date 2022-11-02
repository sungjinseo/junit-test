package dev.greatseo.junittest.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest  // DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired  // DI Test시만 autowired 사용 일반적으로 @ArgContsruct
    private BookRepository bookRepository;

    // [ 데이터준비() + 1 책등록 ] (T) , [ 데이터준비() + 2 책목록보기 ] (T)
    @BeforeEach
    public void 데이터준비() {
        String title = "junit테스트연습";
        String author = "beginner";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    @Test
    @DisplayName("책한권등록 테스트")
    public void 책등록_test(){
        // given
        String title = "테스트북";
        String author = "beginner";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        //when
        Book bookPS = bookRepository.save(book);

        //then
        assertEquals(title, bookPS.getTitle());
    }

    @Test
    @DisplayName("책한권목록 테스트")
    public void 책목록보기_test(){
        // given
        String title = "junit테스트연습";

        // when
        List<Book> bookPS = bookRepository.findAll();

        // then
        assertEquals(title, bookPS.get(0).getTitle());

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("책한권보기 테스트")
    public void 책한권보기_test(){
        //given
        String title = "junit테스트연습";

        //when
        Book bookPS = bookRepository.findById(1L).get();

        //then
        assertEquals(title, bookPS.getTitle());

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test() {
        // given
        Long id = 1L;

        // when
        bookRepository.deleteById(id);

        // then
        assertFalse(bookRepository.findById(id).isPresent());
    }

    // 5. 책 수정
    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("책수정하기 테스트")
    public void 책수정_test() {
        // given
        Long id = 1L;
        String title = "titleismodified";
        String author = "authormodified";
        Book book = new Book(id, title, author);

        // when
        Book bookPS = bookRepository.save(book);

        // then
        assertEquals(id, bookPS.getId());
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

}
