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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miroslav MARKO <mmarko@springsoft.com>
 */
@Entity
@Table(name = "US_INDEX")
@XmlRootElement
public class UsIndex extends Symbol implements Serializable {

    private int status;
    private String underlying_gma_ticker;
    private String company_name;
    private double tick_size;
    private String sedol;
    private String gma_name;
    private String currency;

    public UsIndex() {
        setAsset_type(AssetClass.US_INDEX);
    }

    public UsIndex(int status, String underlying_gma_ticker, String company_name, double tick_size, String sedol, String gma_name, String currency, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.US_INDEX);
        this.status = status;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.company_name = company_name;
        this.tick_size = tick_size;
        this.sedol = sedol;
        this.gma_name = gma_name;
        this.currency = currency;
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

    public String getSedol() {
        return sedol;
    }

    public void setSedol(String sedol) {
        this.sedol = sedol;
    }

    public String getGma_name() {
        return gma_name;
    }

    public void setGma_name(String gma_name) {
        this.gma_name = gma_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.status;
        hash = 53 * hash + Objects.hashCode(this.getActiv_name());
        hash = 53 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 53 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 53 * hash + Objects.hashCode(this.company_name);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.sedol);
        hash = 53 * hash + Objects.hashCode(this.gma_name);
        hash = 53 * hash + Objects.hashCode(this.currency);
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
        final UsIndex other = (UsIndex) obj;
        if (!Objects.equals(this.getActiv_name(), other.getActiv_name())) {
            return false;
        }
        if (!Objects.equals(this.getGma_ticker(), other.getGma_ticker())) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (!Objects.equals(this.company_name, other.company_name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
            return false;
        }
        if (!Objects.equals(this.sedol, other.sedol)) {
            return false;
        }
        if (!Objects.equals(this.gma_name, other.gma_name)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        return true;
    }
}
