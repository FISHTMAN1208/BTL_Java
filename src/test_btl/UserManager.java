package test_btl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
        users.put("admin", new User("admin", "admin", "admin", "Admin Name", "Admin Address", "000-000-0000", "2000-01-01"));
        users.put("user1", new User("user1", "password", "user", "User One", "User One Address", "111-111-1111", "1990-01-01"));
        users.put("user2", new User("user2", "password", "user", "User Two", "User Two Address", "222-222-2222", "1995-01-01"));
    }

    public void addUser(String username, String password, String role, String fullName, String address, String phoneNumber, String dateOfBirth) {
        users.put(username, new User(username, password, role, fullName, address, phoneNumber, dateOfBirth));
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
