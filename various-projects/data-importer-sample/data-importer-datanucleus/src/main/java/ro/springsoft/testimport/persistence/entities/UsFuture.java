/*
 * Copyright (c) 2005-2013 Green Mountain Analytics.
 * This software is the confidential and proprietary information 
 * and shall use it only in accordance with the terms
 * of the license agreement you entered into with Green Mountain Analytics.
 */
package ro.springsoft.testimport.persistence.entities;

import ro.springsoft.testimport.persistence.util.AssetClass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.jdo.annotations.Indices;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miroslav MARKO <mmarko@springsoft.com>
 */
@Entity
@Table(name = "US_FUTURES")
@Indices({@javax.jdo.annotations.Index(name = "gma_ticker_index", members={"gma_ticker"})})
@XmlRootElement
public class UsFuture extends Symbol implements Serializable {

    private int status;
    @Temporal(TemporalType.DATE)
    private Date expiration_date;
    private String company_name;
    @Temporal(TemporalType.DATE)
    private Date expiration_date_short;
    private double currency_multiplayer;
    private String activ_extension;
    private String underlying_gma_ticker;
    private double tick_size;
    @Temporal(TemporalType.DATE)
    private Date last_trading_date;
    private String currency;
    private int multiplier;
    private String root;

    public UsFuture() {
        setAsset_type(AssetClass.US_FUTURES);
    }

    public UsFuture(int status, Date expiration_date, String company_name, Date expiration_date_short, double currency_multiplayer, String activ_extension, String underlying_gma_ticker, double tick_size, Date last_trading_date, String currency, int multiplier, String root, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.US_FUTURES);
        this.status = status;
        this.expiration_date = expiration_date;
        this.company_name = company_name;
        this.expiration_date_short = expiration_date_short;
        this.currency_multiplayer = currency_multiplayer;
        this.activ_extension = activ_extension;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.tick_size = tick_size;
        this.last_trading_date = last_trading_date;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Date getExpiration_date_short() {
        return expiration_date_short;
    }

    public void setExpiration_date_short(Date expiration_date_short) {
        this.expiration_date_short = expiration_date_short;
    }

    public double getCurrency_multiplayer() {
        return currency_multiplayer;
    }

    public void setCurrency_multiplayer(double currency_multiplayer) {
        this.currency_multiplayer = currency_multiplayer;
    }

    public String getActiv_extension() {
        return activ_extension;
    }

    public void setActiv_extension(String activ_extension) {
        this.activ_extension = activ_extension;
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

    public Date getLast_trading_date() {
        return last_trading_date;
    }

    public void setLast_trading_date(Date last_trading_date) {
        this.last_trading_date = last_trading_date;
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
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.getActiv_name());
        hash = 29 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 29 * hash + this.status;
        hash = 29 * hash + Objects.hashCode(this.expiration_date);
        hash = 29 * hash + Objects.hashCode(this.company_name);
        hash = 29 * hash + Objects.hashCode(this.expiration_date_short);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.currency_multiplayer) ^ (Double.doubleToLongBits(this.currency_multiplayer) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.activ_extension);
        hash = 29 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.last_trading_date);
        hash = 29 * hash + Objects.hashCode(this.currency);
        hash = 29 * hash + this.multiplier;
        hash = 29 * hash + Objects.hashCode(this.root);
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
        final UsFuture other = (UsFuture) obj;
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
        if (!Objects.equals(this.company_name, other.company_name)) {
            return false;
        }
        if (!Objects.equals(this.expiration_date_short, other.expiration_date_short)) {
            return false;
        }
        if (Double.doubleToLongBits(this.currency_multiplayer) != Double.doubleToLongBits(other.currency_multiplayer)) {
            return false;
        }
        if (!Objects.equals(this.activ_extension, other.activ_extension)) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
            return false;
        }
        if (!Objects.equals(this.last_trading_date, other.last_trading_date)) {
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
