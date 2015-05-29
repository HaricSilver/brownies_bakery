package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("CalendarConverter")
public class CalendarConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Calendar calendar = null;
		if (arg2 != null && !arg2.isEmpty()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			try {
				Date date = simpleDateFormat.parse(arg2);
				calendar = Calendar.getInstance();
				calendar.setTime(date);
			} catch (ParseException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Invalid date format.",
						"Failed to convert " + arg2 + "");
				throw new ConverterException(message);
			}
		}
		return calendar;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(((Calendar) arg2).getTime());
	}

}
