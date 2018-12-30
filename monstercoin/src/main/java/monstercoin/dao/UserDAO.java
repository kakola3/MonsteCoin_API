package monstercoin.dao;

import monstercoin.entity.User;

import java.util.List;

public interface UserDAO
{
    public User getUser(String login, String password);

    public void saveUser(User theUser);

    public List<User> getUsers();
}

