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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Form bean holding all form data related to Screen2
 *
 * @author Miroslav MARKO
 */
public class AccountDataForm2 extends ActionForm {

    private static final Logger LOG = Logger.getLogger(AccountDataForm2.class.getName());
    private AccountData[] accountData = new AccountData[5];
    private int[] pastDate = new int[5];

    public AccountDataForm2() {
        accountData[0] = new AccountData();
        accountData[1] = new AccountData();
        accountData[2] = new AccountData();
        accountData[3] = new AccountData();
        accountData[4] = new AccountData();
    }

    public int[] getPastDate() {
        return pastDate;
    }

    public String getDate0() {
        return accountData[0].getDate();
    }

    public void setDate0(String date) throws ParseException {
        accountData[0].setDate(date);
    }

    public String getDate1() {
        return accountData[1].getDate();
    }

    public void setDate1(String date) throws ParseException {
        accountData[1].setDate(date);
    }

    public String getDate2() {
        return accountData[2].getDate();
    }

    public void setDate2(String date) throws ParseException {
        accountData[2].setDate(date);
    }

    public String getDate3() {
        return accountData[3].getDate();
    }

    public void setDate3(String date) throws ParseException {
        accountData[3].setDate(date);
    }

    public String getDate4() {
        return accountData[4].getDate();
    }

    public void setDate4(String date) throws ParseException {
        accountData[4].setDate(date);
    }

    public String getCurrency0() {
        return accountData[0].getCurrency();
    }

    public void setCurrency0(String currency) {
        accountData[0].setCurrency(currency);
    }

    public String getCurrency1() {
        return accountData[1].getCurrency();
    }

    public void setCurrency1(String currency) {
        accountData[1].setCurrency(currency);
    }

    public String getCurrency2() {
        return accountData[2].getCurrency();
    }

    public void setCurrency2(String currency) {
        accountData[2].setCurrency(currency);
    }

    public String getCurrency3() {
        return accountData[3].getCurrency();
    }

    public void setCurrency3(String currency) {
        accountData[3].setCurrency(currency);
    }

    public String getCurrency4() {
        return accountData[4].getCurrency();
    }

    public void setCurrency4(String currency) {
        accountData[4].setCurrency(currency);
    }

    public String getAmount0() {
        return accountData[0].getAmount();
    }

    public void setAmount0(String amount) {
        accountData[0].setAmount(amount);
    }

    public String getAmount1() {
        return accountData[1].getAmount();
    }

    public void setAmount1(String amount) {
        accountData[1].setAmount(amount);
    }

    public String getAmount2() {
        return accountData[2].getAmount();
    }

    public void setAmount2(String amount) {
        accountData[2].setAmount(amount);
    }

    public String getAmount3() {
        return accountData[3].getAmount();
    }

    public void setAmount3(String amount) {
        accountData[3].setAmount(amount);
    }

    public String getAmount4() {
        return accountData[4].getAmount();
    }

    public void setAmount4(String amount) {
        accountData[4].setAmount(amount);
    }

    public String getEmail0() {
        return accountData[0].getEmail();
    }

    public void setEmail0(String email) {
        accountData[0].setEmail(email);
    }

    public String getEmail1() {
        return accountData[1].getEmail();
    }

    public void setEmail1(String email) {
        accountData[1].setEmail(email);
    }

    public String getEmail2() {
        return accountData[2].getEmail();
    }

    public void setEmail2(String email) {
        accountData[2].setEmail(email);
    }

    public String getEmail3() {
        return accountData[3].getEmail();
    }

    public void setEmail3(String email) {
        accountData[3].setEmail(email);
    }

    public String getEmail4() {
        return accountData[4].getEmail();
    }

    public void setEmail4(String email) {
        accountData[4].setEmail(email);
    }

    public int getPastDate0() {
        return pastDate[0];
    }

    public int getPastDate1() {
        return pastDate[1];
    }

    public int getPastDate2() {
        return pastDate[2];
    }

    public int getPastDate3() {
        return pastDate[3];
    }

    public int getPastDate4() {
        return pastDate[4];
    }

    public List<AccountData> getPersistable() {

        List<AccountData> retList = new LinkedList<AccountData>();
        for (int i = 0; i < accountData.length; i++) {
            //skip entity with all data null
            if (accountData[i].getAmount() == null && accountData[i].getDate() == null && accountData[i].getEmail() == null) {
                continue;
            }
            //skip entity with all data empty
            if (accountData[i].getAmount().equals("") && accountData[i].getDate().equals("") && accountData[i].getEmail().equals("")) {
                continue;
            }
            retList.add(accountData[i]);

        }
        LOG.info("*** get persitable entities: " + retList);
        return retList;
    }

    public List<AccountData> getAccountDataList() {
        try {
            return DAOFactory.getInstance().getAccountDataDAO().findAll();
        } catch (Exception ex) {
            Logger.getLogger(AccountDataForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void resetPastDate() {
        for (int i = 0; i < pastDate.length; i++) {
            pastDate[i] = 0;
        }
    }

    public void setPastDate() {
        for (int i = 0; i < accountData.length; i++) {
            //skip entity with all data null
            if (accountData[i].getAmount() == null && accountData[i].getDate() == null && accountData[i].getEmail() == null) {
                continue;
            }
            //skip entity with all data empty
            if (accountData[i].getAmount().equals("") && accountData[i].getDate().equals("") && accountData[i].getEmail().equals("")) {
                continue;
            }
            //set condition for readonly ammount field
            try {
                pastDate[i] = accountData[i].getDateAsSqlDate().compareTo(new java.util.Date(new Date().getTime()));
            } catch (Exception ex) {
                LOG.warning(ex.toString());
            }
        }

    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        resetPastDate();
        ActionErrors errors = new ActionErrors();
        boolean dataentered = false;
        for (int i = 0; i < accountData.length; i++) {
            dataentered = validate(accountData[i], i + 1, errors) || dataentered;

        }
        Iterator it = errors.get();
        while (it.hasNext()) {
            LOG.info("**** validation error: " + it.next());
        }

        if (!dataentered) {
            errors.add("common.accountdata2.err",
                    new ActionMessage("error.nodata"));
        }
        return errors;
    }

    private boolean validate(AccountData accountData, int row, ActionErrors errors) {
        //skip validation for empty row
        if (accountData.getAmount() == null && accountData.getDate() == null && accountData.getEmail() == null) {
            return false;
        }
        if (accountData.getAmount().equals("") && accountData.getDate().equals("") && accountData.getEmail().equals("")) {
            return false;
        }
        // begin validate
        if (accountData.getDate() == null || ("".equals(accountData.getDate()))) {
            errors.add("common.accountdata2.err",
                    new ActionMessage("error.date.required.row", row));
        } else {
            try {
                accountData.getDateFormat().setLenient(false);
                Date d = accountData.getDateFormat().parse(accountData.getDate());
                //if date can be parsed remove extra characters
                accountData.setDate(accountData.getDateFormat().format(d));

            } catch (ParseException ex) {
            	LOG.warning(ex.toString());
                errors.add("common.accountdata2.err",
                        new ActionMessage("error.date.required.row", row));
            }
        }

        if (accountData.getCurrency() == null || ("".equals(accountData.getCurrency()))) {
            errors.add("common.accountdata2.err",
                    new ActionMessage("error.currency.required.row", row));
        }

        if (accountData.getAmount() == null || "".equals(accountData.getAmount()) || !NumberUtils.isNumber(accountData.getAmount()) || Float.parseFloat(accountData.getAmount()) < 0) {
            errors.add("common.accountdata2.err",
                    new ActionMessage("error.amount.positive.row", row));
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
            errors.add("common.accountdata2.err",
                    new ActionMessage("error.email.required.row", row));
        } else {
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(accountData.getEmail());
            if (!matcher.matches()) {
                errors.add("common.accountdata2.err",
                        new ActionMessage("error.email.format.row", row));
            }
        }
        return true;
    }
}
