package notadomain.aeras.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PasswordChangeFailRedirect extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionErrors errs = (ActionErrors)request.getSession().getAttribute("ERRORS");
		System.out.println(errs);
		saveErrors(request.getSession(), errs);
		return mapping.findForward("redirect");
	}
}
