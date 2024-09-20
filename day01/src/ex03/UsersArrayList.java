package ex03;

import java.util.Arrays;

public class UsersArrayList implements UserList {
    private User[] users;
    private int size;

    public UsersArrayList() {
        users = new User[10];
        size = 0;
    }

    @Override
    public void addUser(User user) {
        if (size == users.length) {
            users = Arrays.copyOf(users, users.length + users.length / 2);
        }
        users[size++] = user;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; ++i) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID: " + id + " not found");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return users[index];
    }

    @Override
    public int getUserCount() {
        return size;
    }
}
