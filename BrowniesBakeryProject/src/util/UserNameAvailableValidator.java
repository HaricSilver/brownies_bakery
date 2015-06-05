package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.AccountDAO;

@FacesValidator("ValidatorUserName")
public class UserNameAvailableValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String userName = (String) arg2;

		if (AccountDAO.checkUsername(userName)) {
			throw new ValidatorException(new FacesMessage(
					"Username is avaliable"));
		}
	}

}
