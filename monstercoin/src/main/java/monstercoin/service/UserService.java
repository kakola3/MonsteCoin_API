package monstercoin.service;

import monstercoin.entity.User;

import java.util.List;

public interface UserService
{
    public User getUser(String login, String password);

    public void saveUser(User theUser);

    public List<User> getUsers();
}
