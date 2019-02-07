package monstercoin.service;

import monstercoin.dao.WalletDAO;
import monstercoin.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService
{
    @Autowired
    WalletDAO walletDAO;

    @Override
    @Transactional
    public void createWalletForNewUser(Wallet wallet) {
        walletDAO.createWalletForNewUser(wallet);
    }

    @Override
    @Transactional
    public Wallet getWalletPerUser(int user_id) {
       return walletDAO.getWalletPerUser(user_id);
    }

    @Override
    @Transactional
    public void updateWallet(int user_id, String cryptoToUpdate, double cryptoAmount) {
        walletDAO.updateWallet(user_id, cryptoToUpdate, cryptoAmount);
    }

}
