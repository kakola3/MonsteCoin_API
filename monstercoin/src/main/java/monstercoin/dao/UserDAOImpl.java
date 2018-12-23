package monstercoin.dao;


import monstercoin.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<User> theQuery =
                currentSession.createQuery("from User order by email",
                        User.class);

        // execute query and get result list
        List<User> users = theQuery.getResultList();

        // return the results
        return users;
    }

    @Override
    public User getUser(String login) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery =
                currentSession.createQuery("from User", User.class);

        List<User> users = theQuery.getResultList();

        User theUser = new User();

        for (User user :
                users) {
            String tempLogin = login;
            if(user.getLogin().equals(login)){
                System.out.println("If statement: " + login);
                theUser = user;
            }
        }
        // now retrieve/read from database using the login and password
        System.out.println("UserDAOImpl:" + login);
        return theUser;
    }

    @Override
    public void saveUser(User theUser) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save/update the customer ... finally LOL
        currentSession.saveOrUpdate(theUser);
    }
}
