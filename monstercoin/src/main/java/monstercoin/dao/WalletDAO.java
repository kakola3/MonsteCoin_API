package monstercoin.dao;

import monstercoin.entity.Wallet;

import java.util.List;

public interface WalletDAO
{
    public List<Wallet> getWallets();

    public void createWalletForNewUser(Wallet wallet);

    public Wallet getWalletPerUser(int user_id);

    public void updateWallet(int user_id, String cryptoToUpdate, double cryptoAmount);

    public void deleteWallet(int user_id);

    public void saveWalletToCSV(int user_id);
}
