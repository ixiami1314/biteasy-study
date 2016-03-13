package com.biteasy.study.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xiaoxia on 16/3/13.
 */
@Entity
@Table (name = "BITEASY_METAL_QUOTEL")
public class MetalQuoteLogger implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "IDENTITY")
    private String Identity;

    @Column (name = "DELAY")
    private double Delay;

    @Column (name = "NAME")
    private String Name;

    @Column (name = "SYMBOL")
    private String Symbol;

    @Column (name = "UNIT")
    private String Unit;

    @Column (name = "QUOTE_TYPE")
    private String QuoteType;

    @Column (name = "CURRENCY")
    private String Currency;

    @Column (name = "DATE")
    private String Date;

    @Column (name = "TIME")
    private String Time;

    @Column (name = "BID")
    private double Bid;

    @Column (name = "MID")
    private double Mid;

    @Column (name = "ASK")
    private double Ask;

    @Column (name = "SPREAD")
    private double Spread;

    @Column (name = "SOURCE")
    private String Source;


    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public double getDelay() {
        return Delay;
    }

    public void setDelay(double delay) {
        Delay = delay;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getQuoteType() {
        return QuoteType;
    }

    public void setQuoteType(String quoteType) {
        QuoteType = quoteType;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getBid() {
        return Bid;
    }

    public void setBid(double bid) {
        Bid = bid;
    }

    public double getMid() {
        return Mid;
    }

    public void setMid(double mid) {
        Mid = mid;
    }

    public double getAsk() {
        return Ask;
    }

    public void setAsk(double ask) {
        Ask = ask;
    }

    public double getSpread() {
        return Spread;
    }

    public void setSpread(double spread) {
        Spread = spread;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }
}
