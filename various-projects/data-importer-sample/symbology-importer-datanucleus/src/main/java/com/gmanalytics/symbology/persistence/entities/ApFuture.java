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
import javax.jdo.annotations.Indices;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miroslav MARKO <mmarko@gmanalytics.com>
 */
@Entity
@Table(name = "AP_FUTURES")
@Indices({@javax.jdo.annotations.Index(name = "gma_ticker_index", members={"gma_ticker"})})
@XmlRootElement
public class ApFuture extends Symbol implements Serializable {

    private int status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiration_date;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiration_date_short;
    private String root;
    private double currency_multiplier;
    private String company_name;
    private int multiplier;
    private double tick_size;
    private String underlying_gma_ticker;
    private String currency;
    private String activ_extensions;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date last_trading_date;

    public ApFuture() {
        setAsset_type(AssetClass.AP_FUTURES);
    }

    public ApFuture(int status, Date expiration_date, Date expiration_date_short, String root, double currency_multiplier, String company_name, int multiplier, double tick_size, String underlying_gma_ticker, String currency, String activ_extensions, Date last_trading_date, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.AP_FUTURES);
        this.status = status;
        this.expiration_date = expiration_date;
        this.expiration_date_short = expiration_date_short;
        this.root = root;
        this.currency_multiplier = currency_multiplier;
        this.company_name = company_name;
        this.multiplier = multiplier;
        this.tick_size = tick_size;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.currency = currency;
        this.activ_extensions = activ_extensions;
        this.last_trading_date = last_trading_date;
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

    public Date getExpiration_date_short() {
        return expiration_date_short;
    }

    public void setExpiration_date_short(Date expiration_date_short) {
        this.expiration_date_short = expiration_date_short;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public double getCurrency_multiplier() {
        return currency_multiplier;
    }

    public void setCurrency_multiplier(double currency_multiplier) {
        this.currency_multiplier = currency_multiplier;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public double getTick_size() {
        return tick_size;
    }

    public void setTick_size(double tick_size) {
        this.tick_size = tick_size;
    }

    public String getUnderlying_gma_ticker() {
        return underlying_gma_ticker;
    }

    public void setUnderlying_gma_ticker(String underlying_gma_ticker) {
        this.underlying_gma_ticker = underlying_gma_ticker;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getActiv_extensions() {
        return activ_extensions;
    }

    public void setActiv_extensions(String activ_extensions) {
        this.activ_extensions = activ_extensions;
    }

    public Date getLast_trading_date() {
        return last_trading_date;
    }

    public void setLast_trading_date(Date last_trading_date) {
        this.last_trading_date = last_trading_date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.getActiv_name());
        hash = 17 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 17 * hash + this.status;
        hash = 17 * hash + Objects.hashCode(this.expiration_date);
        hash = 17 * hash + Objects.hashCode(this.expiration_date_short);
        hash = 17 * hash + Objects.hashCode(this.root);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.currency_multiplier) ^ (Double.doubleToLongBits(this.currency_multiplier) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.company_name);
        hash = 17 * hash + this.multiplier;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 17 * hash + Objects.hashCode(this.currency);
        hash = 17 * hash + Objects.hashCode(this.activ_extensions);
        hash = 17 * hash + Objects.hashCode(this.last_trading_date);
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
        final ApFuture other = (ApFuture) obj;
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
        if (!Objects.equals(this.expiration_date_short, other.expiration_date_short)) {
            return false;
        }
        if (!Objects.equals(this.root, other.root)) {
            return false;
        }
        if (Double.doubleToLongBits(this.currency_multiplier) != Double.doubleToLongBits(other.currency_multiplier)) {
            return false;
        }
        if (!Objects.equals(this.company_name, other.company_name)) {
            return false;
        }
        if (this.multiplier != other.multiplier) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.activ_extensions, other.activ_extensions)) {
            return false;
        }
        if (!Objects.equals(this.last_trading_date, other.last_trading_date)) {
            return false;
        }
        return true;
    }
}
