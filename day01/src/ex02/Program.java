package ex02;

public class Program {
    public static void main(String[] args) {
        UserList userList = new UsersArrayList();

        User user1 = new User("111", 1000);
        User user2 = new User("222", 2000);
        userList.addUser(user1);
        userList.addUser(user2);

        System.out.println("Total user: " + userList.getUserCount());

        try {
            User foundUser1 = userList.getUserById(1);
            System.out.println("found user id: " + foundUser1);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            User foundUser1 = userList.getUserByIndex(0);
            System.out.println("found user id: " + foundUser1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            User foundUser1 = userList.getUserById(3);
            System.out.println("found user id: " + foundUser1);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
