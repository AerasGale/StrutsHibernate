package notadomain.aeras.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import notadomain.aeras.exception.InvalidCredentialsException;
import notadomain.aeras.service.UserService;
import notadomain.aeras.util.ServiceFactory;

public class GenerateLoginTokenAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LogInForm loginForm = (LogInForm)form;
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		ActionForward fw = mapping.getInputForward();
		ActionErrors errs = new ActionErrors();
		UserService service = ServiceFactory.getUserService();
		
		try {
			String token = service.logInUser(username, password);
			fw = mapping.findForward("loginSuccess");
			Cookie cookie = new Cookie("auth", token);
			cookie.setPath("/StrutsHibernate");
			cookie.setMaxAge(5*60);
			response.addCookie(cookie);
		} catch(InvalidCredentialsException e) {
			fw = mapping.findForward("loginFailure");
			errs.add("loginFailError", new ActionMessage("loginform.error.LoginFail"));
		}finally {
			session.setAttribute("ERRORS", errs);
		}
		
		return fw;
	}

}
