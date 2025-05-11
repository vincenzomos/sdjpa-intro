package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpringBootJPADataSliceTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBookRepository() {

        long countBefore = bookRepository.count();

        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");
        Book savedDDD = bookRepository.save(bookDDD);

        long countAfter = bookRepository.count();

        assertThat(countAfter).isEqualTo(countBefore + 1);
    }

}
