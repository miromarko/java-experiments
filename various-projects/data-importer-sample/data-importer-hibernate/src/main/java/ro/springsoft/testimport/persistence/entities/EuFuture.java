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
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miroslav MARKO <mmarko@springsoft.com>
 */
@Entity
@Table(name = "EU_FUTURES")
@XmlRootElement
public class EuFuture extends Symbol implements Serializable {

    private double currency_multiplier;
    private String activ_extensions;
    private int status;
    private String underlying_gma_ticker;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiration_date;
    private String company_name;
    private double tick_size;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiration_date_short;
    private int multiplier;
    private String currency;

    public EuFuture() {
        setAsset_type(AssetClass.EU_FUTURES);
    }

    public EuFuture(double currency_multiplier, String activ_extensions, int status, String underlying_gma_ticker, Date expiration_date, String company_name, double tick_size, Date expiration_date_short, int multiplier, String currency, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.EU_FUTURES);
        this.currency_multiplier = currency_multiplier;
        this.activ_extensions = activ_extensions;
        this.status = status;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.expiration_date = expiration_date;
        this.company_name = company_name;
        this.tick_size = tick_size;
        this.expiration_date_short = expiration_date_short;
        this.multiplier = multiplier;
        this.currency = currency;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUnderlying_gma_ticker() {
        return underlying_gma_ticker;
    }

    public void setUnderlying_gma_ticker(String underlying_gma_ticker) {
        this.underlying_gma_ticker = underlying_gma_ticker;
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

    public double getTick_size() {
        return tick_size;
    }

    public void setTick_size(double tick_size) {
        this.tick_size = tick_size;
    }

    public Date getExpiration_date_short() {
        return expiration_date_short;
    }

    public void setExpiration_date_short(Date expiration_date_short) {
        this.expiration_date_short = expiration_date_short;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.getActiv_name());
        hash = 17 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.currency_multiplier) ^ (Double.doubleToLongBits(this.currency_multiplier) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.activ_extensions);
        hash = 17 * hash + this.status;
        hash = 17 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 17 * hash + Objects.hashCode(this.expiration_date);
        hash = 17 * hash + Objects.hashCode(this.company_name);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.expiration_date_short);
        hash = 17 * hash + this.multiplier;
        hash = 17 * hash + Objects.hashCode(this.currency);
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
        final EuFuture other = (EuFuture) obj;
        if (!Objects.equals(this.getActiv_name(), other.getActiv_name())) {
            return false;
        }
        if (!Objects.equals(this.getGma_ticker(), other.getGma_ticker())) {
            return false;
        }
        if (Double.doubleToLongBits(this.currency_multiplier) != Double.doubleToLongBits(other.currency_multiplier)) {
            return false;
        }
        if (!Objects.equals(this.activ_extensions, other.activ_extensions)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (!Objects.equals(this.expiration_date, other.expiration_date)) {
            return false;
        }
        if (!Objects.equals(this.company_name, other.company_name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
            return false;
        }
        if (!Objects.equals(this.expiration_date_short, other.expiration_date_short)) {
            return false;
        }
        if (this.multiplier != other.multiplier) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        return true;
    }
}
