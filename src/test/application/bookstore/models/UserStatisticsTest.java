package application.bookstore.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserStatisticsTest {


    @Test
    void getListOFUsers() {
        assertNotNull(UserStatistics.getListOFUsers());
    }


    @Test
    void getAmount() {
        UserStatistics user = new UserStatistics(10,new User("kejsib","admin",Role.ADMIN));
        assertEquals(10,user.getAmount());
    }

    @Test
    void setAmount() {
        UserStatistics user = new UserStatistics(10,new User("kejsib","admin",Role.ADMIN));
        int newAmount = 25;
        user.setAmount(newAmount);
        assertEquals(25,user.getAmount());
    }

    @Test
    void getUser() {
        UserStatistics user = new UserStatistics(10,new User("kejsib","admin",Role.ADMIN));
        assertEquals(new User("kejsib","admin",Role.ADMIN),user.getUser());
    }


    @Test
    void returnUserIndexTrue() {
        User newUser = new User("kejsib", "testpassword");
        UserStatistics userStats = new UserStatistics(10, newUser);
        UserStatistics.getListOFUsers().add(userStats);

        assertEquals(0, UserStatistics.returnUserIndex(newUser));
    }

    @Test
    void returnUserIndexFalse(){
        User newUser = new User("kejsib", "testpassword");
        UserStatistics userStats = new UserStatistics(10, newUser);
        UserStatistics.getListOFUsers().add(userStats);

        User falseUser = new User("NotUser", "NotUserpassword");
        assertEquals(-1, UserStatistics.returnUserIndex(falseUser));
    }

    @Test
    void getListOfUsers(){
        ArrayList<UserStatistics> userList = new ArrayList<>();
        UserStatistics user = new UserStatistics(10,new User("kejsib","admin",Role.ADMIN));
        UserStatistics user1 = new UserStatistics(10,new User("kejtlinb", "testpassword",Role.MANAGER));
        UserStatistics user2 = new UserStatistics(10,new User("erinm", "testpassword",Role.LIBRARIAN));
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        ArrayList<UserStatistics> result = UserStatistics.getListOFUsers();
        assertEquals(3,result.size());
    }


}