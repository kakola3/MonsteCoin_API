package monstercoin.dao;

import javafx.stage.FileChooser;
import monstercoin.entity.Wallet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WalletDAOImpl implements WalletDAO
{
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Wallet> getWallets() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<Wallet> theQuery =
                currentSession.createQuery("from Wallet",
                        Wallet.class);

        // execute query and get result list
        List<Wallet> wallets = theQuery.getResultList();
        System.out.println("users: |" + wallets + "|");

        // return the results
        return wallets;
    }

    @Override
    public void createWalletForNewUser(Wallet wallet) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(wallet);
    }

    @Override
    public Wallet getWalletPerUser(int user_id) {
        Session currentSession = sessionFactory.getCurrentSession();


        Query<Wallet> theQuery =
                currentSession.createQuery("from Wallet", Wallet.class);

        List<Wallet> wallets = theQuery.getResultList();

        Wallet theWallet = new Wallet();

        for (Wallet wallet :
                wallets) {
            if(wallet.getUser_id() == user_id){
                theWallet = wallet;
            }
        }
        return theWallet;
    }

    @Override
    public void updateWallet(int user_id, String cryptoToUpdate, double cryptoAmount) {
        Session currentSession = sessionFactory.getCurrentSession();

        if(cryptoToUpdate.equals("bitcoin")){
            Query<Wallet> theQuery = currentSession.createQuery("update Wallet set bitcoin_amount = :cryptoAmount" +
                    " where user_id = :user_id");
            theQuery.setParameter("cryptoAmount", cryptoAmount);
            theQuery.setParameter("user_id", user_id);
            int result = theQuery.executeUpdate();
            System.out.println("wallet: user_id: " + user_id + " and cryptoToUpdate: |" + cryptoToUpdate + "| and cryptoAmount: " + cryptoAmount);
        }
        else if(cryptoToUpdate.equals("ethereum")){
            Query<Wallet> theQuery = currentSession.createQuery("update Wallet set ethereum_amount = :cryptoAmount" +
                    " where user_id = :user_id");
            theQuery.setParameter("cryptoAmount", cryptoAmount);
            theQuery.setParameter("user_id", user_id);
            int result = theQuery.executeUpdate();
            System.out.println("wallet: user_id: " + user_id + " and cryptoToUpdate: |" + cryptoToUpdate + "| and cryptoAmount: " + cryptoAmount);
        }
        else if(cryptoToUpdate.equals("litecoin")){
            Query<Wallet> theQuery = currentSession.createQuery("update Wallet set litecoin_amount = :cryptoAmount" +
                    " where user_id = :user_id");
            theQuery.setParameter("cryptoAmount", cryptoAmount);
            theQuery.setParameter("user_id", user_id);
            int result = theQuery.executeUpdate();
            System.out.println("wallet: user_id: " + user_id + " and cryptoToUpdate: |" + cryptoToUpdate + "| and cryptoAmount: " + cryptoAmount);
        }
        else if(cryptoToUpdate.equals("xrp")){
            Query<Wallet> theQuery = currentSession.createQuery("update Wallet set xrp_amount = :cryptoAmount" +
                    " where user_id = :user_id");
            theQuery.setParameter("cryptoAmount", cryptoAmount);
            theQuery.setParameter("user_id", user_id);
            int result = theQuery.executeUpdate();
            System.out.println("wallet: user_id: " + user_id + " and cryptoToUpdate: |" + cryptoToUpdate + "| and cryptoAmount: " + cryptoAmount);
        }
        else if(cryptoToUpdate.equals("eos")){
            Query<Wallet> theQuery = currentSession.createQuery("update Wallet set eos_amount = :cryptoAmount" +
                    " where user_id = :user_id");
            theQuery.setParameter("cryptoAmount", cryptoAmount);
            theQuery.setParameter("user_id", user_id);
            int result = theQuery.executeUpdate();
            System.out.println("wallet: user_id: " + user_id + " and cryptoToUpdate: |" + cryptoToUpdate + "| and cryptoAmount: " + cryptoAmount);
        }


    }

    @Override
    public void deleteWallet(int user_id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Wallet where user_id = :user_id");
        theQuery.setParameter("user_id", user_id);

        int result = theQuery.executeUpdate();
    }

    @Override
    public void saveWalletToCSV(int user_id) {
        FileChooser fileChooser = new FileChooser();
        String filePath = fileChooser.showSaveDialog(null).getAbsolutePath();
        System.out.println("filePath: " + filePath);
    }

}
