package monstercoin.service;

import monstercoin.entity.User;

import java.util.List;

public interface UserService
{
    public User getUser(String login, String password);

    public void saveUser(User theUser);

    public List<User> getUsers();

    public int userExist(User theUser);

    public void updateAccountBallance(int user_id, double ballance);

    public double getUserBallance(int id);
}
