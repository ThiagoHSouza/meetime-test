package br.com.meetime.exceptions;

/**
 * Excess generated if an error occurs while handling the database.
 * @author thiago_souza07
 *
 */
public class RepositoryError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String defaultRepositoryException = "Erro ao %s registro : %s";
	
	public static final String CREATE = "criar";
	public static final String UPDATE = "alterar";
	public static final String FIND = "buscar";
	public static final String DELETE = "remover";

	public RepositoryError(String message) {
		super(message);
	}
	
	public RepositoryError(String type, Throwable e) {
		super(String.format(defaultRepositoryException, type, e.getMessage()), e);
	}
	
	

}
