// UserManager.java
package test_btl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
        users.put("admin", new User("admin", "admin"));
        users.put("user1", new User("user1", "user", "user"));
        users.put("user2", new User("user2", "user", "user"));
    }

    public void addUser(String username, String password, String role) {
        users.put(username, new User(username, password, role));
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword() != null && user.getPassword().equals(password);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
