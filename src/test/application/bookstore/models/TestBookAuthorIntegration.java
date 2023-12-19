package application.bookstore.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static application.bookstore.models.Category.CLASSIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBookAuthorIntegration {

    @Test
    void testBookAuthorIntegration() {
        Author author = new Author("Ismail", "Kadare");
        author.saveInFile();

        Book book = new Book("testisbn", "Prilli i thyer", 600, 800, author, CLASSIC, LocalDate.of(2023, 2, 18), "3", 4);

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(author);
        assertEquals(1, books.size());
        assertEquals(1, authors.size());
        assertEquals(author, books.get(0).getAuthor());

    }
    @Test
            void newAuthorTest(){
        Author author = new Author("Ismail", "Kadare");
        author.saveInFile();
        Book book = new Book("testisbn", "Prilli i thyer", 600, 800, author, CLASSIC, LocalDate.of(2023, 2, 18), "3", 4);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        ArrayList<Author> authors = new ArrayList<Author>();
        Author newAuthor = new Author("Dritero", "Agolli");
        newAuthor.saveInFile();
        book.setAuthor(newAuthor);
        book.saveInFile();
        assertEquals(newAuthor, book.getAuthor());


    }
}
