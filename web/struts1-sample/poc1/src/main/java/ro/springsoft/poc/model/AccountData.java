package ro.springsoft.poc.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AccountData entity - persistent this class maps ACCOUNTDATA table
 *
 * @author Miroslav MARKO
 */
public class AccountData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date = null;
    private String currency;
    private String amount;
    private String email;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private NumberFormat nrFormat;

    public AccountData() {
    }

    public AccountData(Date date, String currency, float amount, String email) {
        this.currency = currency;
        this.amount = amount + "";
        this.email = email;
        this.date = dateFormat.format(date);
    }

    public AccountData(String date, String currency, float amount, String email) {
        this.date = date;
        this.currency = currency;
        this.amount = amount + "";
        this.email = email;
    }

    public Date getDateAsSqlDate() {
        try {
            getDateFormat().setLenient(false);
            return new Date(this.getDateFormat().parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(AccountData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = dateFormat.format(date);

    }

    public void setDate(String date) {
        this.date = date;
    }

    public NumberFormat getNrFormat() {
        return nrFormat;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
        Locale locale = null;
        if (currency != null && !currency.equals("")) {
            //USD, EUR, GBP, SGD, AUD
            if (currency.equals("US")) {
                locale = new Locale("en", "US");
            } else if (currency.equals("EUR")) {
                locale = new Locale("de", "DE");
            } else if (currency.equals("GBP")) {
                locale = Locale.UK;
            } else if (currency.equals("SGD")) {
                locale = new Locale("zh", "SG");
            } else if (currency.equals("AUD")) {
                locale = new Locale("en", "AU");
            }
        }

        if (locale == null) {
            nrFormat = NumberFormat.getCurrencyInstance();
        } else {
            nrFormat = NumberFormat.getCurrencyInstance(locale);
        }
    }

    public String getAmount() {
        return amount;
    }

    public float getAmountAsFloat() {
        return Float.parseFloat(amount);
    }

    public String getAmountAsCurrency() {

        if (nrFormat != null) {
            return nrFormat.format(getAmountAsFloat());
        }
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateFormat getDateFormat() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "AccountData{" + "date=" + date + ", currency=" + currency + ", amount=" + getAmountAsCurrency() + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 73 * hash + (this.currency != null ? this.currency.hashCode() : 0);
        hash = 73 * hash + (this.amount != null ? this.amount.hashCode() : 0);
        hash = 73 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(this == obj)
        	return true;
        if(!(obj instanceof AccountData))
        	return false;
        	
        final AccountData other = (AccountData) obj;
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        if ((this.currency == null) ? (other.currency != null) : !this.currency.equals(other.currency)) {
            return false;
        }
        if ((this.amount == null) ? (other.amount != null) : !this.amount.equals(other.amount)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }
}
