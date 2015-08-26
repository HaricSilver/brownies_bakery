package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// String password = (String) value;
		// String confirm = (String) component.getAttributes().get("confirm");

		UIInput passwordUI = (UIInput) component.getAttributes().get("pass");
		String password = (String) passwordUI.getValue();
		String confirm = (String) value;

		System.out.println("Password validator [pass: " + password + ", repass: " + confirm + "]");

		if (password == null || confirm == null) {
			return; // Just ignore and let required="true" do its job.
		}

		if (!password.trim().equalsIgnoreCase(confirm.trim())) {
			throw new ValidatorException(new FacesMessage("Passwords are not equal."));
		}
	}

}
