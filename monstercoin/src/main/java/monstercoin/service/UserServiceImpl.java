package monstercoin.service;

import monstercoin.dao.UserDAO;
import monstercoin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    // need to inject customer dao
    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getUsers();
    }


    @Override
    @Transactional
    public User getUser(String login, String password) {
        System.out.println("UserServiceImpl class: " + login + " " + password);
        return userDAO.getUser(login, password);
    }

    @Override
    @Transactional
    public void saveUser(User theUser) {
        userDAO.saveUser(theUser);
    }

    @Override
    @Transactional
    public int userExist(User theUser){
        return userDAO.userExist(theUser);
    }

    @Override
    @Transactional
    public void updateAccountBallance(User user, double ballance) {
        userDAO.updateAccountBallance(user, ballance);
    }

    @Override
    @Transactional
    public double getUserBallance(int id) {
        return userDAO.getUserBallance(id);
    }
}
