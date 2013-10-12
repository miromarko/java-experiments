package ro.springsoft.poc.action;

import ro.springsoft.poc.form.AccountDataForm;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action executed on Submit button from Screen1
 *
 * @author Miroslav MARKO
 */
public class Screen1SubmitAction extends Action{
    private static final Logger LOG = Logger.getLogger(Screen1SubmitAction.class.getName());
    

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AccountDataForm screen1Form = (AccountDataForm) form;
        screen1Form.setPastDate();
        LOG.info("**** Screen1 submited: "+screen1Form);
        return mapping.findForward("submit_screen1");
    }
    
    
    
}
