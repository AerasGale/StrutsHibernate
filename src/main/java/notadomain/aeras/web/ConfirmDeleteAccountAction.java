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

import notadomain.aeras.service.UserService;
import notadomain.aeras.util.ServiceFactory;

public class ConfirmDeleteAccountAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Cookie[] cookies = request.getCookies();
		Cookie auth = null;
		for(Cookie c:cookies) {
			if(c.getName().equals("auth")){
				auth = c;
			}
		}
		
		ActionForward fw = mapping.findForward("unauthorized");
		ActionErrors errs = new ActionErrors();
		if(auth == null) {
			return fw;
		}
		String token = auth.getValue();
		
		UserService service = ServiceFactory.getUserService();
		
		if(service.deleteUser(token)) {
			fw = mapping.findForward("deleteSuccess");
			auth.setMaxAge(0);
			auth.setPath("/StrutsHibernate");
			response.addCookie(auth);
		} else {
			fw = mapping.findForward("deleteFail");
			errs.add("unauthorizedAccess", new ActionMessage("deleteuserform.error.UnauthorizedAccess"));
		}
		request.getSession().setAttribute("ERRORS", errs);
		
		return fw;
	}

}
