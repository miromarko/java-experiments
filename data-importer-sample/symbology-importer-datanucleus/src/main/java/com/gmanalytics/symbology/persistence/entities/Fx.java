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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miroslav MARKO <mmarko@gmanalytics.com>
 */
@Entity
@Table(name = "FX")
@Indices({@javax.jdo.annotations.Index(name = "gma_ticker_index", members={"gma_ticker"})})
@XmlRootElement
public class Fx extends Symbol implements Serializable {

    private String to_currency;
    private int status;
    private String underlying_gma_ticker;
    private double tick_size;
    private String gma_name;
    private String currency;

    public Fx() {
        setAsset_type(AssetClass.FX);
    }

    public Fx(String to_currency, int status, String underlying_gma_ticker, double tick_size, String gma_name, String currency, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.FX);
        this.to_currency = to_currency;
        this.status = status;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.tick_size = tick_size;
        this.gma_name = gma_name;
        this.currency = currency;
    }

    public String getTo_currency() {
        return to_currency;
    }

    public void setTo_currency(String to_currency) {
        this.to_currency = to_currency;
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

    public double getTick_size() {
        return tick_size;
    }

    public void setTick_size(double tick_size) {
        this.tick_size = tick_size;
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
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.getActiv_name());
        hash = 67 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 67 * hash + Objects.hashCode(this.to_currency);
        hash = 67 * hash + this.status;
        hash = 67 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.gma_name);
        hash = 67 * hash + Objects.hashCode(this.currency);
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
        final Fx other = (Fx) obj;
        if (!Objects.equals(this.getActiv_name(), other.getActiv_name())) {
            return false;
        }
        if (!Objects.equals(this.getGma_ticker(), other.getGma_ticker())) {
            return false;
        }
        if (!Objects.equals(this.to_currency, other.to_currency)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
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
