/*
 * Copyright (c) 2005-2013 Green Mountain Analytics.
 * This software is the confidential and proprietary information 
 * and shall use it only in accordance with the terms
 * of the license agreement you entered into with Green Mountain Analytics.
 */
package com.gmanalytics.symbology.persistence.entities;

import com.gmanalytics.symbology.persistence.util.AssetClass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miroslav MARKO <mmarko@gmanalytics.com>
 */
@Entity
@Table(name = "US_FUTURE_OPTIONS")
@XmlRootElement
public class UsFutureOption extends Symbol implements Serializable {

    private int status;
    @Temporal(TemporalType.DATE)
    private Date expiration_date;
    private char callput;
    private double strike_price;
    private double currency_multiplier;
    private String activ_extensions;
    private String underlying_gma_ticker;
    private double tick_size;
    private String publisher;
    private String currency;
    private int multiplier;
    private String root;

    public UsFutureOption() {
        setAsset_type(AssetClass.US_FUTURE_OPTIONS);
    }

    public UsFutureOption(int status, Date expiration_date, char callput, double strike_price, double currency_multiplier, String activ_extensions, String underlying_gma_ticker, double tick_size, String publisher, String currency, int multiplier, String root, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.US_FUTURE_OPTIONS);
        this.status = status;
        this.expiration_date = expiration_date;
        this.callput = callput;
        this.strike_price = strike_price;
        this.currency_multiplier = currency_multiplier;
        this.activ_extensions = activ_extensions;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.tick_size = tick_size;
        this.publisher = publisher;
        this.currency = currency;
        this.multiplier = multiplier;
        this.root = root;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public char getCallput() {
        return callput;
    }

    public void setCallput(char callput) {
        this.callput = callput;
    }

    public double getStrike_price() {
        return strike_price;
    }

    public void setStrike_price(double strike_price) {
        this.strike_price = strike_price;
    }

    public double getCurrency_multiplier() {
        return currency_multiplier;
    }

    public void setCurrency_multiplier(double currency_multiplier) {
        this.currency_multiplier = currency_multiplier;
    }

    public String getActiv_extensions() {
        return activ_extensions;
    }

    public void setActiv_extensions(String activ_extensions) {
        this.activ_extensions = activ_extensions;
    }

    public String getUnderlying_gma_ticker() {
        return underlying_gma_ticker;
    }

    public void setUnderlying_gma_ticker(String underlying_gma_ticker) {
        this.underlying_gma_ticker = underlying_gma_ticker;
    }

    public double getTick_size() {
        return tick_size;
    }

    public void setTick_size(double tick_size) {
        this.tick_size = tick_size;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.getActiv_name());
        hash = 71 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 71 * hash + this.status;
        hash = 71 * hash + Objects.hashCode(this.expiration_date);
        hash = 71 * hash + this.callput;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.strike_price) ^ (Double.doubleToLongBits(this.strike_price) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.currency_multiplier) ^ (Double.doubleToLongBits(this.currency_multiplier) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.activ_extensions);
        hash = 71 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.publisher);
        hash = 71 * hash + Objects.hashCode(this.currency);
        hash = 71 * hash + this.multiplier;
        hash = 71 * hash + Objects.hashCode(this.root);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsFutureOption other = (UsFutureOption) obj;
        if (!Objects.equals(this.getActiv_name(), other.getActiv_name())) {
            return false;
        }
        if (!Objects.equals(this.getGma_ticker(), other.getGma_ticker())) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.expiration_date, other.expiration_date)) {
            return false;
        }
        if (this.callput != other.callput) {
            return false;
        }
        if (Double.doubleToLongBits(this.strike_price) != Double.doubleToLongBits(other.strike_price)) {
            return false;
        }
        if (Double.doubleToLongBits(this.currency_multiplier) != Double.doubleToLongBits(other.currency_multiplier)) {
            return false;
        }
        if (!Objects.equals(this.activ_extensions, other.activ_extensions)) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (this.multiplier != other.multiplier) {
            return false;
        }
        if (!Objects.equals(this.root, other.root)) {
            return false;
        }
        return true;
    }
}
