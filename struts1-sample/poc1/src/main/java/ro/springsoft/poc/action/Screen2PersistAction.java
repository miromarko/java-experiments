package ro.springsoft.poc.action;

import ro.springsoft.poc.form.AccountDataForm2;
import ro.springsoft.poc.model.AccountData;
import ro.springsoft.poc.persistence.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action executed on Continue button from Preview Data - Screen1
 *
 * @author Miroslav MARKO
 */
public class Screen2PersistAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AccountDataForm2 screen2Form = (AccountDataForm2) form;
        //persist AccountData
        for (AccountData accounData : screen2Form.getPersistable()) {
            DAOFactory.getInstance().getAccountDataDAO().create(accounData);
        }
        return mapping.findForward("screen2persisted");
    }
}
