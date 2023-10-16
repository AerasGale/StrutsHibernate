package notadomain.aeras.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class LogInForm extends ActionForm {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errs = new ActionErrors();
		
		if(username.isEmpty() || username == null || username.trim().length() == 0) {
			System.out.println("Username req error");
			errs.add("usernameRequiredError", new ActionMessage("loginform.error.UsernameRequired"));
		}
		if(password.isEmpty() || password == null || password.trim().length() == 0) {
			System.out.println("Password req error");
			errs.add("passwordRequiredError", new ActionMessage("loginform.error.PasswordRequired"));
		}
		
		request.getSession().setAttribute("ERRORS", errs);
		
		return errs;
	}
	
	

}
