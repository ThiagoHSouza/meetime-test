package br.com.meetime.models;

/**
 * It enforces the get and set implementation of the id in the model
 * @author thiago_souza07
 *
 */
public abstract class AbstractModel {
	public abstract Long getId();
	public abstract void setId(Long id);
}
