package ro.springsoft.poc.form;

import ro.springsoft.poc.model.AccountData;
import ro.springsoft.poc.persistence.DAOFactory;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Form bean holding all form data related to Screen1 
 *
 * @author Miroslav MARKO
 */
public class AccountDataForm extends ActionForm {

    private static final Logger LOG = Logger.getLogger(AccountDataForm.class.getName());
    private AccountData accountData;
    private int pastDate;

    public AccountDataForm() {
        accountData = new AccountData("2013-11-20", "EUR", 125.25f, "bruce@gmail.com");
        setPastDate();
    }

    public int getPastDate() {
        return pastDate;
    }

    public final void setPastDate() {
        pastDate = accountData.getDateAsSqlDate().compareTo(new java.util.Date(new Date().getTime()));
        LOG.log(Level.INFO, "**** pastDate: {0}", pastDate);
    }

    public String getDate() {
        return accountData.getDate();
    }

    public void setDate(String date) throws ParseException {
        accountData.setDate(date);
    }

    public String getCurrency() {
        return accountData.getCurrency();
    }

    public void setCurrency(String currency) {
        accountData.setCurrency(currency);
    }

    public String getAmount() {
        return accountData.getAmount();
    }

    public void setAmount(String amount) {
        accountData.setAmount(amount);
    }

    public String getEmail() {
        return accountData.getEmail();
    }

    public void setEmail(String email) {
        accountData.setEmail(email);
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public List<AccountData> getAccountDataList() {
        try {
            return DAOFactory.getInstance().getAccountDataDAO().findAll();
        } catch (Exception ex) {
            Logger.getLogger(AccountDataForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Standards struts validation for all form data.
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (accountData.getDate() == null || ("".equals(accountData.getDate()))) {
            errors.add("common.accountdata.err",
                    new ActionMessage("error.date.required"));
        } else {
            try {
                accountData.getDateFormat().setLenient(false);
                Date d = accountData.getDateFormat().parse(accountData.getDate());
                //if date can be parsed remove extra characters
                accountData.setDate(accountData.getDateFormat().format(d));
            } catch (ParseException ex) {
            	LOG.warning(ex.toString());
                errors.add("common.accountdata.err",
                        new ActionMessage("error.date.required"));
            }
        }

        if (accountData.getCurrency() == null || ("".equals(accountData.getCurrency()))) {
            errors.add("common.accountdata.err",
                    new ActionMessage("error.currency.required"));
        }
        if (accountData.getAmount() == null || "".equals(accountData.getAmount()) || !NumberUtils.isNumber(accountData.getAmount()) || Float.parseFloat(accountData.getAmount()) < 0) {
            errors.add("common.accountdata.err",
                    new ActionMessage("error.amount.positive"));
        } else {
            // set amount as currency ronded value
            NumberFormat fmt = accountData.getNrFormat();
            if (fmt != null) {
                try {
                    //try to round amount accourding currency if posible
                    accountData.setAmount(fmt.parse(accountData.getAmountAsCurrency()).floatValue() + "");
                } catch (ParseException ex) {
                	LOG.warning(ex.toString());
                    
                }
            }
        }
        //validate email
        String email_pattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (accountData.getEmail() == null || "".equals(accountData.getEmail())) {
            errors.add("common.accountdata.err",
                    new ActionMessage("error.email.required"));
        } else {
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(accountData.getEmail());
            if (!matcher.matches()) {
                errors.add("common.accountdata.err",
                        new ActionMessage("error.email.format"));
            }
        }

        return errors;
    }

    @Override
    public String toString() {
        return "AccountDataForm: " + accountData.toString();
    }
}
