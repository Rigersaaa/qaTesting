package application.bookstore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static application.bookstore.models.Category.CLASSIC;
import static org.testng.AssertJUnit.assertEquals;

class BookTest {

    Book book;
    @BeforeEach
    void setUp(){
        Book book = new Book("testisbn","Prilli i thyer",600,800,new Author("Ismail","Kadare"),CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
    }

    @Test
    void TestGetTitle(){
        Book book = new Book("testisbn","Prilli i thyer",600,800,new Author("Ismail","Kadare"),CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        assertEquals("Prilli i thyer",book.getTitle());
    }

    @Test
    void TestSetQuantity(){
        Book book = new Book("testisbn","Prilli i thyer",600,800,new Author("Ismail","Kadare"),CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        int quantity = 5;
        book.setQuantity(quantity);
        assertEquals(5,book.getQuantity());
    }
    @Test
    void testGetCategory(){
        Book book = new Book("testisbn","Prilli i thyer",600,800,new Author("Ismail","Kadare"),CLASSIC, LocalDate.of(2023, 2, 18),"3",4);
        assertEquals(CLASSIC,book.getCategory());

    }

    @Test
    void getBookbyTitleTrue(){
        book.saveToNewFile2();
        assertEquals(book,Book.getBookByTitle2("Prilli i thyer"));
    }
    @Test
    void getBookByTitleNull() {
        book.saveToNewFile2();
        assertEquals(null, book.getBookByTitle2("Pallati i Endrrave"));
    }


    @Test
    void getBookByISBNFalse() {
        book.saveToNewFile();
        assertEquals(-1,book.getBookByISBN("testisbn1"));
    }


    @Test
    void testGetBooksNull() {
        assertEquals(0, Book.getBooks().size());
    }






}