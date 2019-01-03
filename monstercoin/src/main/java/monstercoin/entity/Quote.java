package monstercoin.entity;

import javax.persistence.*;


// One to many one xd dodac murzyn

@Entity
@Table(name = "quote")
public class Quote
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fiat_symbol")
    private String fiat_symbol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quote_detail_id")
    private QuoteDetail quoteDetail;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "crypto_currency_id")
    private CryptoCurrency cryptoCurrency;

    public Quote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFiat_symbol() {
        return fiat_symbol;
    }

    public void setFiat_symbol(String fiat_symbol) {
        this.fiat_symbol = fiat_symbol;
    }

    public QuoteDetail getQuoteDetail() {
        return quoteDetail;
    }

    public void setQuoteDetail(QuoteDetail quoteDetail) {
        this.quoteDetail = quoteDetail;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", fiat_symbol='" + fiat_symbol + '\'' +
                ", quoteDetail=" + quoteDetail +
                ", cryptoCurrency=" + cryptoCurrency +
                '}';
    }
}
