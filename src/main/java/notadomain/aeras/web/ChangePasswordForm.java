package notadomain.aeras.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class ChangePasswordForm extends ActionForm {
	private String oldPassword;
	private String newPassword;
	private String rePassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errs = new ActionErrors();
		if(oldPassword.isEmpty() || oldPassword == null || oldPassword.trim().length() == 0) {
			errs.add("passwordRequiredError", new ActionMessage("changepasswordform.error.PasswordRequired"));
		}
		if(newPassword.isEmpty() || newPassword == null || newPassword.trim().length() == 0) {
			errs.add("passwordRequiredError", new ActionMessage("changepasswordform.error.PasswordRequired"));
		} else if(!newPassword.equals(rePassword)) {
			errs.add("passwordMismatchError", new ActionMessage("changepasswordform.error.PasswordMismatch"));
		}
		request.getSession().setAttribute("ERRORS", errs);
		
		return errs;
	}

}
