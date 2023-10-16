package notadomain.aeras.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


@SuppressWarnings("serial")
public class SaveUserForm extends ActionForm {
	public String username;
	public String password;
	public String repassword;
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
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errs = new ActionErrors();
		
		if(username.isEmpty() || username == null || username.trim().length() == 0) {
			errs.add("usernameRequiredError", new ActionMessage("registerform.error.UsernameRequired"));
		}
		if(password.isEmpty() || password == null || password.trim().length() == 0) {
			errs.add("passwordRequiredError", new ActionMessage("registerform.error.PasswordRequired"));
		} 
		else if(!checkPassword()) {
			errs.add("passwordInvalidError", new ActionMessage("registerform.error.PasswordInvalid"));
		}
		else if(!password.equals(repassword)) {
			errs.add("passwordMismatchError", new ActionMessage("registerform.error.PasswordMismatch"));
		}
		request.getSession().setAttribute("ERRORS", errs);

		System.out.println(errs);
		return errs;
	}
	
	private boolean checkPassword() {
		if(password.length()<8 || password.length()>20) {
			return false;
		}
		return true;
	}
}
