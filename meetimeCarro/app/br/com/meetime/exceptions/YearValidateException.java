package br.com.meetime.exceptions;


/**
 * The car is older than allowed by the system.
 * @author thiago_souza07
 *
 */
public class YearValidateException extends MeetimeException {

	private static final long serialVersionUID = 1L;

	public YearValidateException(String string) {
		super(string);
	}
	
	public YearValidateException(String string, Throwable e) {
		super(string, e);
	}
	

}
