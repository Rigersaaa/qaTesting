package application.bookstore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static application.bookstore.models.Category.CLASSIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBookAuthorOrdered_BookIntegration {

    private Book book;
    private Author author;
    private Ordered_Book orderedBook;

    @BeforeEach
    public void setUp() {
        author = new Author("Ismail", "Kadare");
        Book book = new Book("testisbn","Prilli i thyer",600,800,author,CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        orderedBook = new Ordered_Book(book.getTitle(), book.getPurchasedPrice(), 2);
    }

    @Test
    public void testOrderedBookTotalAmount() {
        float expectedTotalAmount = 1200;
        float actualTotalAmount = orderedBook.getTotamount();
        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    public void testBookAuthor() {
        author = new Author("Ismail", "Kadare");
        Book book = new Book("testisbn","Prilli i thyer",600,800,author,CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        orderedBook = new Ordered_Book(book.getTitle(), book.getPurchasedPrice(), 2);
        Author expectedAuthor = author;
        Author actualAuthor = book.getAuthor();
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void testBookPrice() {
        author = new Author("Ismail", "Kadare");
        Book book = new Book("testisbn","Prilli i thyer",600,800,author,CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        orderedBook = new Ordered_Book(book.getTitle(), book.getPurchasedPrice(), 2);
        double expectedPrice = 600.0;
        double actualPrice = book.getPurchasedPrice();
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testBookQuantity() {
        author = new Author("Ismail", "Kadare");
        Book book = new Book("testisbn","Prilli i thyer",600,800,author,CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        orderedBook = new Ordered_Book(book.getTitle(), book.getPurchasedPrice(), 2);
        int expectedQuantity = 4;
        int actualQuantity = book.getQuantity();
        assertEquals(expectedQuantity, actualQuantity);
    }
}
