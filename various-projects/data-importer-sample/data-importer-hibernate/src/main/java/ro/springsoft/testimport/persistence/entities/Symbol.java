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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miroslav MARKO <mmarko@springsoft.com>
 */
//@NamedQueries({
//    @NamedQuery(name = "Symbology.findSimbol", query = "SELECT s FROM Symbol s WHERE s.gma_ticker = :gma_ticker")
//})
@XmlRootElement
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name = "SYMBOLS")
public class Symbol implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
//    @ElementCollection(fetch = FetchType.LAZY)
    @Temporal(TemporalType.TIMESTAMP)
    private Date generated_time;
    private String gma_ticker;
    private String activ_name;
    @Enumerated(EnumType.STRING)
    private AssetClass asset_type;

    public Symbol() {
    }

    public Symbol(Date generated_time, String gma_ticker, String activ_name, AssetClass asset_type) {
        this.generated_time = generated_time;
        this.gma_ticker = gma_ticker;
        this.activ_name = activ_name;
        this.asset_type = asset_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public Date getGenerated_time() {
        return generated_time;
    }

    public void setGenerated_time(Date generated_time) {
        this.generated_time = generated_time;
    }

    public String getGma_ticker() {
        return gma_ticker;
    }

    public void setGma_ticker(String gma_ticker) {
        this.gma_ticker = gma_ticker;
    }

    public String getActiv_name() {
        return activ_name;
    }

    public void setActiv_name(String activ_name) {
        this.activ_name = activ_name;
    }

    public AssetClass getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(AssetClass asset_type) {
        this.asset_type = asset_type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Symbol other = (Symbol) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Symbol{" + "id=" + id + ", generated_time=" + generated_time + ", gma_ticker=" + gma_ticker + ", activ_name=" + activ_name + ", asset_type=" + asset_type + '}';
    }
    
}
