package monstercoin.entity;

import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;

@Entity
@Table(name = "quote_detail")
public class QuoteDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "volume_24h")
    private double volume_24h;

    @Column(name = "volume_24h_change_24h")
    private double volume_24h_change_24h;

    @Column(name = "market_cap")
    private double market_cap;

    @Column(name = "market_cap_change_24h")
    private double market_cap_change_24h;

    @Column(name = "percent_change_1h")
    private double percent_change_1h;

    @Column(name = "percent_change_12h")
    private double percent_change_12h;

    @Column(name = "percent_change_24h")
    private double percent_change_24h;

    @Column(name = "percent_change_7d")
    private double percent_change_7d;

    @Column(name = "percent_change_30d")
    private double percent_change_30d;

    @Column(name = "percent_change_1y")
    private double percent_change_1y;

    @Column(name = "ath_price")
    private double ath_price;

    @Column(name = "ath_date")
    private String ath_date;

    @Column(name = "percent_from_price_ath")
    private double percent_from_price_ath;

    public QuoteDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(double volume_24h) {
        this.volume_24h = volume_24h;
    }

    public double getVolume_24h_change_24h() {
        return volume_24h_change_24h;
    }

    public void setVolume_24h_change_24h(double volume_24h_change_24h) {
        this.volume_24h_change_24h = volume_24h_change_24h;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public double getMarket_cap_change_24h() {
        return market_cap_change_24h;
    }

    public void setMarket_cap_change_24h(double market_cap_change_24h) {
        this.market_cap_change_24h = market_cap_change_24h;
    }

    public double getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(double percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public double getPercent_change_12h() {
        return percent_change_12h;
    }

    public void setPercent_change_12h(double percent_change_12h) {
        this.percent_change_12h = percent_change_12h;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public double getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(double percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public double getPercent_change_30d() {
        return percent_change_30d;
    }

    public void setPercent_change_30d(double percent_change_30d) {
        this.percent_change_30d = percent_change_30d;
    }

    public double getPercent_change_1y() {
        return percent_change_1y;
    }

    public void setPercent_change_1y(double percent_change_1y) {
        this.percent_change_1y = percent_change_1y;
    }

    public double getAth_price() {
        return ath_price;
    }

    public void setAth_price(double ath_price) {
        this.ath_price = ath_price;
    }

    public String getAth_date() {
        return ath_date;
    }

    public void setAth_date(String ath_date) {
        this.ath_date = ath_date;
    }

    public double getPercent_from_price_ath() {
        return percent_from_price_ath;
    }

    public void setPercent_from_price_ath(double percent_drom_price_ath) {
        this.percent_from_price_ath = percent_drom_price_ath;
    }

    @Override
    public String toString() {
        return "QuoteDetail{" +
                "id=" + id +
                ", price=" + price +
                ", volume_24h=" + volume_24h +
                ", volume_24h_change_24h=" + volume_24h_change_24h +
                ", market_cap=" + market_cap +
                ", market_cap_change_24h=" + market_cap_change_24h +
                ", percent_change_1h=" + percent_change_1h +
                ", percent_change_12h=" + percent_change_12h +
                ", percent_change_24h=" + percent_change_24h +
                ", percent_change_7d=" + percent_change_7d +
                ", percent_change_30d=" + percent_change_30d +
                ", percent_change_1y=" + percent_change_1y +
                ", ath_price=" + ath_price +
                ", ath_date='" + ath_date + '\'' +
                ", percent_drom_price_ath=" + percent_from_price_ath +
                '}';
    }
}
