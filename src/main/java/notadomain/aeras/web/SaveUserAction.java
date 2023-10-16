package notadomain.aeras.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import notadomain.aeras.service.UserService;
import notadomain.aeras.util.ServiceFactory;

public class SaveUserAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SaveUserForm userform = (SaveUserForm)form;
		String username = userform.getUsername();
		String password = userform.getPassword();
		
		ActionForward fw = mapping.getInputForward();
		ActionErrors errs = new ActionErrors();
		
		
		UserService service = ServiceFactory.getUserService();
		
		if(service.addUser(username, password)) {
			fw = mapping.findForward("registrationSuccess");
		}else {
			errs.add("usernameTakenError", new ActionMessage("registerform.error.UsernameTaken"));
			fw = mapping.findForward("registrationFailure");
		}
		
		request.getSession().setAttribute("ERRORS", errs);
		
		return fw;
	}
}
