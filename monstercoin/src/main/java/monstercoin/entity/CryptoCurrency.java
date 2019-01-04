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

    @Column(name = "rank")
    private int rank;

    @Column(name = "circulating_supply")
    private long circulating_supply;

    @Column(name = "max_supply")
    private long max_supply;

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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(long circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public long getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(long max_supply) {
        this.max_supply = max_supply;
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
                ", rank='" + rank + '\'' +
                ", circulating_supply=" + circulating_supply +
                ", max_supply=" + max_supply +
                ", last_updated='" + last_updated + '\'' +
                ", quotes=" + quotes +
                '}';
    }

    // add convenience methods for bi-directional relationship
    public void add(Quote tempQuote){
        if(quotes == null){
            quotes = new ArrayList<>();
        }

        quotes.add(tempQuote);
        setQuotes(quotes);

        tempQuote.setCryptoCurrency(this);
    }
}
