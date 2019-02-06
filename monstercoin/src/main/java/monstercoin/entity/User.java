package monstercoin.entity;


import javax.persistence.*;
import javax.servlet.annotation.HttpConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    @NotEmpty
    @Size(min=6)
    private String login;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 6)
    private String password;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "balance_account")
    @NotNull
    private double ballance_account;

    public User() {

    }

    public User(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBallance_account() {
        return ballance_account;
    }

    public void setBallance_account(double ballance_account) {
        this.ballance_account = ballance_account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
