package monstercoin.entity;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


// One to many one xd dodac murzyn

@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "last_updated")
    private String last_updated;

    @OneToMany(mappedBy = "cryptoCurrency",
                cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                            CascadeType.DETACH, CascadeType.REFRESH}) // was mapped in quote class
    private List<Quote> quotes;


    public CryptoCurrency() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", last_updated='" + last_updated + '\'' +
                '}';
    }

    // add convenience methods for bi-directional relationship
    public void add(Quote tempQuote){
        if(quotes == null){
            quotes = new ArrayList<>();
        }

        quotes.add(tempQuote);

        tempQuote.setCryptoCurrency(this);
    }
}
