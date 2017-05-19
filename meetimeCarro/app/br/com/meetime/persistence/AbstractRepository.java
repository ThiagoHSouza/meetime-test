package br.com.meetime.persistence;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import br.com.meetime.exceptions.RepositoryError;
import br.com.meetime.models.AbstractModel;

/**
 * Provides the basic methods of manipulating the database.
 * @author thiago_souza07
 *
 * @param <E>
 */
public abstract class AbstractRepository<E extends AbstractModel>{
		
	protected abstract Long getIndex();
	protected abstract List<E> getBase();
	
	
	/**
	 * Error Messages.
	 */
	private static final String MSG_ERROR_ID_NULL = "Favor informar o id do objeto.";
	private static final String MSG_ERROR_ID_NOT_FOUND = "Não foi possível localizar o id informado.";
	private static final String MSG_ERROR_NOT_FOUND = "Nenhum registro encontrado.";
	private static final String MSG_ERROR_ENTITY_NULL = "Objeto não pode ser null.";
	
	
	public AbstractRepository() {
	}
	
	/**
	 * Creates registration on the database.
	 * @param entity
	 * @return
	 */
	public E create(E entity) {
		try {
			Objects.requireNonNull(entity, MSG_ERROR_ENTITY_NULL);
			entity.setId(getIndex());
			getBase().add(entity);
			return entity;
			
		} catch (Exception e) {
			throw new RepositoryError(RepositoryError.CREATE, e);
		}
	}
	
	/**
	 * Changes the registration on the database.
	 * @param entity
	 * @return
	 */
	public E update(E entity){
		try {
			Objects.requireNonNull(entity.getId(), MSG_ERROR_ID_NULL);
			E ent = getById(entity.getId());
			Objects.requireNonNull(ent, MSG_ERROR_ID_NOT_FOUND);
			remove(ent.getId());
			getBase().add(entity);
			return entity;
		} catch (Exception e) {
			throw new RepositoryError(RepositoryError.UPDATE, e);
		}
	}

	/**
	 * Removes the registration on the database.
	 * @param entity
	 */
	public void remove(E entity) {
		try {
			Objects.requireNonNull(entity, MSG_ERROR_ENTITY_NULL);
		} catch (Exception e) {
			throw new RepositoryError(RepositoryError.DELETE, e);
		}
		remove(entity.getId());
	}

	/**
	 * Removes the registration on the database.
	 * @param id
	 */
	public void remove(Long id) {
		try {
			Objects.requireNonNull(id, MSG_ERROR_ID_NULL);
			E ent = getById(id);
			
			
			Objects.requireNonNull(ent, MSG_ERROR_NOT_FOUND);
			getBase().remove(ent);		
			
		} catch (Exception e) {
			throw new RepositoryError(RepositoryError.DELETE, e);
		}
	}
	
	/**
	 * Searches for a record in the database.
	 * @param id
	 * @return
	 */
	public E findById(Long id) {
		try {
			Objects.requireNonNull(id, MSG_ERROR_ID_NULL);
			E ent = getById(id);
			
			Objects.requireNonNull(ent, MSG_ERROR_NOT_FOUND);
			return ent;
			
		} catch (Exception e) {
			throw new RepositoryError(RepositoryError.FIND, e);
		}
	}
	
	/**
	 * Search all records of a type in the database.
	 * @param id
	 * @return
	 */
	public List<E> findAll(){
		try {
			List<E> list = getBase();
			Objects.requireNonNull(list, MSG_ERROR_NOT_FOUND);
			list.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));
			return list;
			
		} catch (Exception e) {
			throw new RepositoryError(RepositoryError.FIND, e);
		}
	}
	
	private E getById(Long id){
		return getBase().stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}

