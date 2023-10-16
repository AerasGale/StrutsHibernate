package notadomain.aeras.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import notadomain.aeras.exception.UnauthorizedAccessException;
import notadomain.aeras.service.UserService;
import notadomain.aeras.util.ServiceFactory;

public class UpdatePasswordAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChangePasswordForm changePasswordForm = (ChangePasswordForm)form;
		UserService service = ServiceFactory.getUserService();
		String oldPassword = changePasswordForm.getOldPassword();
		String newPassword = changePasswordForm.getNewPassword();
		
		Cookie[] cookies = request.getCookies();
		Cookie auth = null;
		for(Cookie c:cookies) {
			if(c.getName().equals("auth")){
				auth = c;
			}
		}
		
		ActionForward fw = mapping.findForward("unauthorized");
		if(auth == null) {
			return fw;
		}
		String token = auth.getValue();
		
		
		ActionErrors errs = new ActionErrors();
		try {
			service.changePassword(oldPassword, newPassword, token);
			fw =  mapping.findForward("updateSuccess");
		} catch (UnauthorizedAccessException e) {
			fw = mapping.findForward("updateFail");
			errs.add("unauthorizedAccess", new ActionMessage("changepasswordform.error.UnauthorizedAccess"));
		}
		request.getSession().setAttribute("ERRORS", errs);
		return fw;
	}
}
