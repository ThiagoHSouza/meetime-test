package br.com.meetime.exceptions;


/**
 * Exception generated if the car is invalid
 * @author thiago_souza07
 *
 */
public class CarValidateException extends MeetimeException {

	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Erro ao salvar o vículo: %s";

	public CarValidateException(String message) {
		super(String.format(defaultMessage, message));
	}
	
	public CarValidateException(String message, Throwable e) {
		super(String.format(defaultMessage, message), e);
	}
	

}
