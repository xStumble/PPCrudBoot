package me.xstumble.ppcrudboot.service;

import me.xstumble.ppcrudboot.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(long id, User user);
    void deleteUser(long id);
    User getUser(long id);
    List<User> getAllUsers();

}
