package monstercoin.service;

import monstercoin.entity.Wallet;

public interface WalletService
{
    public void createWalletForNewUser(Wallet wallet);

    public Wallet getWalletPerUser(int user_id);

    public void updateWallet(int user_id, String cryptoToUpdate, double cryptoAmount);
}
