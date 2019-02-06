package monstercoin.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "crypto_transaction")
public class CryptoTransaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "currency")
    @NotEmpty
    private String currency;

    @Column(name = "amount")
    @NotNull
    @DecimalMin("0.001") // maybe error ',' != '.'
    private double amount;

    @Column(name = "action")
    @NotEmpty
    private String action;

    @Column(name = "price")
    @DecimalMin("0.001") // maybe error ',' != '.'
    private double price;

    @Column(name = "action_date")
    @NotEmpty
    private String action_date;

    @Column(name = "order_status")
    @NotEmpty
    private String order_status;

    @Column(name = "user_id")
    @NotNull
    @Min(1)
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public CryptoTransaction() {
    }

    public CryptoTransaction(int id, User user){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAction_date() {
        return action_date;
    }

    public void setAction_date(String action_date) {
        this.action_date = action_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
