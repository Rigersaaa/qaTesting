package application.bookstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.*;

class AuthorTest {

    @Test
    void TestGetFullName() {
        Author author = new Author("Ismail", "Kadare");
        Assertions.assertEquals("Ismail Kadare", author.getFullName());
    }


   @Test
    void TestGetFirstName(){
       Author author  = new Author("Ismail", "Kadare");
       Assertions.assertEquals("Ismail",author.getFirstName());
    }

    @Test
    void TestSetFirstName(){
        Author author  = new Author("Ismail", "Kadare");
        String newFirstName = "Dritero";
        author.setFirstName(newFirstName);
        Assertions.assertEquals("Dritero",author.getFirstName());
    }

    @Test
    void TestGetLastName(){
        Author author  = new Author("Ismail", "Kadare");
        Assertions.assertEquals("Kadare",author.getLastName());
    }

    @Test
    void TestSetLastName(){
        Author author  = new Author("Ismail", "Kadare");
        String newLastName = "Agolli";
        author.setLastName(newLastName);
        Assertions.assertEquals("Agolli",author.getLastName());
    }


    @Test
    public void testSaveInFileTrue() {
        Author author  = new Author("Naim", "Frasheri");
        assertTrue(author.saveInFile());
        ArrayList<Author> authors = Author.getAuthors();
        assertTrue(authors.contains(author));
    }
    @Test
    public void testSaveInFileFalse() {
        Author author  = new Author("Dritero", "Agolli");
        Author author1  = new Author("Naim", "Frasheri");
        ArrayList<Author> authors = Author.getAuthors();
        assertFalse(authors.contains(author1));
    }

    @Test
    void TestIsValidTrue(){
     Author author = new Author("Ismail","Kadare");
     assertTrue(author.isValid());
    }
    @Test
    void TestIsValidFalse(){
        Author author = new Author("","K");
        assertFalse(author.isValid());
    }
    @Test
    public void testDeleteAuthorFromFile() {
        Author author = new Author("Ismail","Kadare");
        assertTrue(author.deleteFromFile());
        ArrayList<Author> authors = Author.getAuthors();
        assertFalse(authors.contains(author));
    }
    @Test
    public  void testToString(){
        Author author = new Author("Ismail","Kadare");
        String result = "Ismail Kadare";
        assertEquals(result,author.toString());
    }

    @Test
    public void FindAuthorFromTheFile(){
        Author author= new Author("Ismail","Kadare");
        author.saveInFile();
        Assertions.assertEquals(author,Author.findAuthor(author));
    }

    @Test
    public void FindAuthorFromTheFileFalse(){
        Author author = new Author("Ismail","");
        author.saveInFile();
        Assertions.assertSame(null,Author.findAuthor(author));
    }



    }




