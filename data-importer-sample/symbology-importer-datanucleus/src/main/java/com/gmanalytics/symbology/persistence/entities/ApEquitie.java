/*
 * Copyright (c) 2005-2013 Green Mountain Analytics.
 * This software is the confidential and proprietary information 
 * Information and shall use it only in accordance with the terms
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
@Table(name = "AP_EQUITIES")
@Indices({@javax.jdo.annotations.Index(name = "gma_ticker_index", members={"gma_ticker"})})
@XmlRootElement
public class ApEquitie extends Symbol implements Serializable {

    private String sedol;
    private String currency;
    private String gma_name;
    private int status;
    private String activ_extensions;
    private String company_name;
    private String underlying_gma_ticker;
    private double tick_size;

    public ApEquitie() {
        setAsset_type(AssetClass.AP_EQUITIES);
    }

    public ApEquitie(String sedol, String currency, String gma_name, int status, String activ_extensions, String company_name, String underlying_gma_ticker, double tick_size, Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        super(generated_time, gma_ticker, activ_name, asset_type);
        setAsset_type(AssetClass.AP_EQUITIES);
        this.sedol = sedol;
        this.currency = currency;
        this.gma_name = gma_name;
        this.status = status;
        this.activ_extensions = activ_extensions;
        this.company_name = company_name;
        this.underlying_gma_ticker = underlying_gma_ticker;
        this.tick_size = tick_size;
    }

    public String getSedol() {
        return sedol;
    }

    public void setSedol(String sedol) {
        this.sedol = sedol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGma_name() {
        return gma_name;
    }

    public void setGma_name(String gma_name) {
        this.gma_name = gma_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActiv_extensions() {
        return activ_extensions;
    }

    public void setActiv_extensions(String activ_extensions) {
        this.activ_extensions = activ_extensions;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.getActiv_name());
        hash = 61 * hash + Objects.hashCode(this.getGma_ticker());
        hash = 61 * hash + Objects.hashCode(this.sedol);
        hash = 61 * hash + Objects.hashCode(this.currency);
        hash = 61 * hash + Objects.hashCode(this.gma_name);
        hash = 61 * hash + this.status;
        hash = 61 * hash + Objects.hashCode(this.activ_extensions);
        hash = 61 * hash + Objects.hashCode(this.company_name);
        hash = 61 * hash + Objects.hashCode(this.underlying_gma_ticker);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.tick_size) ^ (Double.doubleToLongBits(this.tick_size) >>> 32));
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
        final ApEquitie other = (ApEquitie) obj;
        if (!Objects.equals(this.getActiv_name(), other.getActiv_name())) {
            return false;
        }
        if (!Objects.equals(this.getGma_ticker(), other.getGma_ticker())) {
            return false;
        }
        if (!Objects.equals(this.sedol, other.sedol)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.gma_name, other.gma_name)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.activ_extensions, other.activ_extensions)) {
            return false;
        }
        if (!Objects.equals(this.company_name, other.company_name)) {
            return false;
        }
        if (!Objects.equals(this.underlying_gma_ticker, other.underlying_gma_ticker)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tick_size) != Double.doubleToLongBits(other.tick_size)) {
            return false;
        }
        return true;
    }
}
