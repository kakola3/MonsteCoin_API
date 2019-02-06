package monstercoin.dao;

import monstercoin.entity.Wallet;

public interface WalletDAO
{
    public void createWalletForNewUser(Wallet wallet);

    public Wallet getWalletPerUser(int user_id);

    public void updateWallet(int user_id);
}
