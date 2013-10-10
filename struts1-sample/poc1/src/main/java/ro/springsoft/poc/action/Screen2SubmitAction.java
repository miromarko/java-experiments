package ro.springsoft.poc.action;

import ro.springsoft.poc.form.AccountDataForm2;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action executed on Submit button from Screen2
 *
 * @author Miroslav MARKO
 */
public class Screen2SubmitAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AccountDataForm2 accountDataForm2 = (AccountDataForm2) form;
        accountDataForm2.setPastDate();
        return mapping.findForward("submit_screen2");
    }
}
