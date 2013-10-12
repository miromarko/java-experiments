package ro.springsoft.poc.action;

import ro.springsoft.poc.form.AccountDataForm;
import ro.springsoft.poc.persistence.DAOFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action executed on Continue button from Preview Data - Screen1
 *
 * @author Miroslav MARKO
 */
public class Screen1PersistAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AccountDataForm screen1Form = (AccountDataForm) form;
        //persist AccountData
        DAOFactory.getInstance().getAccountDataDAO().create(screen1Form.getAccountData());
        return mapping.findForward("screen1persisted");
    }
}
