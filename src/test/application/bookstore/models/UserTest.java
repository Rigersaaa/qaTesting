package application.bookstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

class UserTest {


    @Test
    void save() {
    }

    @Test
    void getUsername() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        assertEquals("kejsib", userTest.getUsername());
    }

    @Test
    public void SetUsername() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        userTest.setUsername("kejsibushi");
        assertEquals("kejsibushi", userTest.getUsername());
    }

    @Test
    void getPassword() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        assertEquals("admin",userTest.getPassword());
    }

    @Test
    public void SetPassword() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        userTest.setPassword("TestPassword");
        assertEquals("TestPassword", userTest.getPassword());
    }


    @Test
    void getRole() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        assertEquals(Role.ADMIN,userTest.getRole());
    }

    @Test
    public void testSetRole() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        userTest.setRole(Role.MANAGER);
        assertEquals(Role.MANAGER, userTest.getRole());
    }

    @Test
    void testToString() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
       String result = "User{username=kejsib, password=admin, role=ADMIN}";
       assertEquals(result,userTest.toString());
    }

    @Test
    public void testEqualsFalse() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        User userTest2 = new User("kejtlinb","admin",Role.ADMIN);
        assertFalse(userTest.equals(userTest2));
    }

    @Test
    public void testEqualsTrue() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        User userTest2 = new User("kejsib","admin",Role.ADMIN);
        assertTrue(userTest.equals(userTest2));
    }

    @Test
    void testGetIfExistsTrue() {
        User user1 = new User("kejsib", "testPassword");
        User user2 = new User("kejtlinb", "testPassword2");
        User user3 = new User("erinm", "testPassword3");
        User.getUsers().add(user1);
        User.getUsers().add(user2);
        User.getUsers().add(user3);
        User potentialUser1 = new User("erinm", "testPassword3");
        assertEquals(user3, User.getIfExists(potentialUser1));
    }

    @Test
    void testGetIfExistsFalse(){
        User user1 = new User("kejsib", "testPassword");
        User user2 = new User("kejtlinb", "testPassword2");
        User user3 = new User("erinm", "testPassword3");
        User.getUsers().add(user1);
        User.getUsers().add(user2);
        User.getUsers().add(user3);
        User potentialUser3 = new User("sindiA", "testpassword4");
        assertNull(User.getIfExists(potentialUser3));
    }

    @Test
    void validateUsername() {
        User user1 = new User("kejsib", "testPassword");
        assertEquals("kejsib",user1.validateUsername(user1));
    }

    @Test
    void getUsersEmpty() {
        ArrayList<User> user1 = new ArrayList<User>();
        int size = 0;
        assertEquals(size,user1.size());
    }

    @Test
    void getUsers(){
        ArrayList<User> user1 = new ArrayList<User>();
        User user = new User("kejsib", "testPassword");
        User user2 = new User("kejtlinb", "testPassword2");
        User user3 = new User("erinm", "testPassword3");
        user1.add(user);
        user1.add(user2);
        user1.add(user3);
        int expectedSize = 3;
        assertEquals(expectedSize, user1.size());

    }
    @Test
    public void testSaveInFileFalse() {
        User userTest = new User("kejsib","admin",Role.ADMIN);
        userTest.getUsers().add(userTest);
        File file = new File(User.FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        boolean saved = userTest.SaveInFile();
        Assertions.assertFalse(saved);
    }




}