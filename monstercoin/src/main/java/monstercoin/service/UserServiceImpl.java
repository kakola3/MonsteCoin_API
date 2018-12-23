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
    public User getUser(String login) {
        System.out.println("UserServiceImpl class: " + login);
        return userDAO.getUser(login);
    }


    @Override
    @Transactional
    public void saveUser(User theUser) {
        userDAO.saveUser(theUser);
    }
}
