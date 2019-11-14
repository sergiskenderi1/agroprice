package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesContextUtil {

	public static void facesContext(String message1, String message2) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(message1, message2));
	}
}

