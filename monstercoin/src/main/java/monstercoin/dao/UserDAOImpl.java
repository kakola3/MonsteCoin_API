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
                currentSession.createQuery("from User",
                        User.class);

        // execute query and get result list
        List<User> users = theQuery.getResultList();
        System.out.println("users: |" + users + "|");

        // return the results
        return users;
    }

    @Override
    public User getUser(String login, String password) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery =
                currentSession.createQuery("from User", User.class);

        List<User> users = theQuery.getResultList();

        User theUser = new User();

        for (User user :
                users) {
            System.out.println("user: " + user);
            System.out.println("login: |" + login + "|  password: |" + password + "|");
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                System.out.println("If statement: " + login + " " + password);
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

    @Override
    public int userExist(User theUser) {
         //all Users in database
        List<User> users = getUsers();
        String theUserLogin = theUser.getLogin().trim();
        System.out.println("theUser.getLogin=" + theUser.getLogin());
        String theUserEmail = theUser.getEmail().trim();
        System.out.println("theUser.getEmail=" + theUser.getEmail());

        int value = 99;

        // search matching User
        for (User user:
                users) {
            String userLogin = user.getLogin();
            String userEmail = user.getEmail();
            System.out.println("theUserLogin: |" + theUserLogin + "| theUserEmail: |" + theUserEmail + "|");
            System.out.println("usrLogin: |" + userLogin + "|");
            if (userLogin.equals(theUserLogin)) {
                System.out.println("theUserLogin: |" + theUserLogin + "|");
                if(userEmail.equals(theUserEmail)){
                    System.out.println("User with login " + theUserLogin + " and email " + theUserEmail + " already exist");
                    value = 1;
                    System.out.println("value: " + value);
                }else{
                    System.out.println("User with login " + theUserLogin + " already exist");
                    value = 2;
                    System.out.println("value: " + value);
                }

            } else if (userEmail.equals(theUserEmail)) {
                System.out.println("User with email " + theUserEmail + " already exist");
                value = 3;
                System.out.println("value: " + value);

            } else{
                value = 0; // User is not exist in database
                System.out.println("value: " + value);
            }
        }
        System.out.println("value: " + value);
        return value;
    }

    @Override
    public void updateAccountBallance(int user_id, double ballance) {

        Session currentSession = sessionFactory.getCurrentSession();

//        User theUser = new User();
//        theUser.setId(user_id);
//
//        double beforeTransactionBallance = theUser.getBallance_account();
//        System.out.println("beforeTransactionBallance: " + beforeTransactionBallance);

        Query<User> theQuery =
                currentSession.createQuery("update User set balance_account = :ballance" +
                        " where id = :user_id");
        theQuery.setParameter("ballance", ballance);
        theQuery.setParameter("user_id", user_id);
        int result = theQuery.executeUpdate();
    }

    @Override
    public double getUserBallance(int id){
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery =
                currentSession.createQuery("from User where id = :user_id");
        theQuery.setParameter("user_id", id);
        User theUser = theQuery.getSingleResult();
        System.out.println("theUser: " + theQuery.getSingleResult());

        double tempUserBallance = theUser.getBallance_account();
        System.out.println("theUser.getBallance_account(): " + theUser.getBallance_account());

        return tempUserBallance;
    }

    @Override
    public void deleteUser(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from User where id = :id");
        theQuery.setParameter("id", id);

        int result = theQuery.executeUpdate();
    }


}
