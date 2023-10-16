package notadomain.aeras.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogOutAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Cookie auth = new Cookie("auth", "");
		auth.setMaxAge(0);
		auth.setPath("/StrutsHibernate");
		response.addCookie(auth);
		return mapping.findForward("logOut");
	}
}
