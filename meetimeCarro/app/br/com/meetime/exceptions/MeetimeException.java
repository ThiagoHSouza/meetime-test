package br.com.meetime.exceptions;


/**
 * Exception default of the system.
 * @author thiago_souza07
 *
 */
public class MeetimeException extends Exception {

	private static final long serialVersionUID = 1L;

	public MeetimeException(String string) {
		super(string);
	}
	
	public MeetimeException(String string, Throwable e) {
		super(string, e);
	}
	

}
