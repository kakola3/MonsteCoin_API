package monstercoin.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wallet")
public class Wallet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "bitcoin_amount")
    private double bitcoin_amount;

    @Column(name = "ethereum_amount")
    private double ethereum_amount;

    @Column(name = "litecoin_amount")
    private double litecoin_amount;

    @Column(name = "xrp_amount")
    private double xrp_amount;

    @Column(name = "eos_amount")
    private double eos_amount;

    public Wallet(){

    }

    public Wallet(@NotNull int user_id, double bitcoin_amount, double ethereum_amount, double litecoin_amount, double xrp_amount, double eos_amount) {
        this.user_id = user_id;
        this.bitcoin_amount = bitcoin_amount;
        this.ethereum_amount = ethereum_amount;
        this.litecoin_amount = litecoin_amount;
        this.xrp_amount = xrp_amount;
        this.eos_amount = eos_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getBitcoin_amount() {
        return bitcoin_amount;
    }

    public void setBitcoin_amount(double bitcoin_amount) {
        this.bitcoin_amount = bitcoin_amount;
    }

    public double getEthereum_amount() {
        return ethereum_amount;
    }

    public void setEthereum_amount(double ethereum_amount) {
        this.ethereum_amount = ethereum_amount;
    }

    public double getLitecoin_amount() {
        return litecoin_amount;
    }

    public void setLitecoin_amount(double litecoin_amount) {
        this.litecoin_amount = litecoin_amount;
    }

    public double getXrp_amount() {
        return xrp_amount;
    }

    public void setXrp_amount(double xrp_amount) {
        this.xrp_amount = xrp_amount;
    }

    public double getEos_amount() {
        return eos_amount;
    }

    public void setEos_amount(double eos_amount) {
        this.eos_amount = eos_amount;
    }
}
