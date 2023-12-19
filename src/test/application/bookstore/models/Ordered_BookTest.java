package application.bookstore.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertNotEquals;

class Ordered_BookTest {
     String title = "Prilli i thyer";
     float price = 600;
     int quantity = 5;


    @Test
    void getTitle() {
        Ordered_Book book = new Ordered_Book(title,price,quantity);
        assertEquals(title, book.getTitle());
    }

    @Test
    void getPrice() {
        Ordered_Book book = new Ordered_Book(title,price,quantity);
        assertEquals(title, book.getPrice());
    }

    @Test
    void getQuantity() {
        Ordered_Book book = new Ordered_Book(title,price,quantity);
        assertEquals(title, book.getQuantity());
    }
    @Test
    void testTotalPriceToPay(){
        Ordered_Book book = new Ordered_Book(title,price,quantity);
        assertEquals(3000,book.getTotamount());
    }

    @Test
    void testTotalPriceToPayFalse(){
        Ordered_Book book = new Ordered_Book(title,price,quantity);
        assertNotEquals(3001,book.getTotamount());
    }


    @Test
    void testToString(){
        Ordered_Book book = new Ordered_Book(title,price,quantity);
        assertEquals("Title :Prilli i thyer    Price :  600.0 Nr of copies : 5  Total Amount for this book   3000.0",book.toString());
    }

}