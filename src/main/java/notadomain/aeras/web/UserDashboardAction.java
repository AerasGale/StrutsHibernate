package notadomain.aeras.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import notadomain.aeras.service.UserService;
import notadomain.aeras.util.ServiceFactory;

public class UserDashboardAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward fw = mapping.findForward("unauthorized");
		UserService service = ServiceFactory.getUserService();
		Cookie[] cookies = request.getCookies();
		Cookie auth = null;
		for(Cookie c:cookies) {
			if(c.getName().equals("auth")){
				auth = c;
			}
		}
		if(auth != null) {
			String username = service.verifyUserAndGetUsername(auth.getValue());
			request.setAttribute("username", username);
			fw = mapping.findForward("userDashboard");
		}
		return fw;
	}
}
