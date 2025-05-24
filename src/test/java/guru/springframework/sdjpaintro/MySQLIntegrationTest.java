package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repositories.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import guru.springframework.sdjpaintro.repositories.BookUuidRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by jt on 7/4/21.
 */
@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    private AuthorUuidRepository authorUuidRepository;

    @Autowired
    private BookUuidRepository bookUuidRepository;


    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

    }

    @Test
    void testAuthorUUID() {
        var author = new AuthorUuid();
        author.setFirstName("John");
        author.setLastName("Smith");
        AuthorUuid savedAuthor = authorUuidRepository.save(author);
        UUID uuid = savedAuthor.getId();
        System.out.println("UUID: " + uuid);
        AuthorUuid foundAuthor = authorUuidRepository.findById(uuid).get();

        assertThat(foundAuthor).isNotNull();
        assertThat(foundAuthor.getFirstName()).isEqualTo("John");
    }

    @Test
    void testBookUUID() {
        // Assuming BookUuid has a title property and a default constructor
        var book = new BookUuid();
        book.setTitle("My Awesome Book with UUID");
        // Set other properties as needed, e.g., isbn, publisher

        BookUuid savedBook = bookUuidRepository.save(book);
        UUID uuid = savedBook.getId(); // Assuming getId() returns the UUID
        System.out.println("Book UUID: " + uuid);

        // Retrieve the book by its UUID
        BookUuid foundBook = bookUuidRepository.findById(uuid).orElse(null);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("My Awesome Book with UUID");
        // Add more assertions for other properties if you set them
    }

}


