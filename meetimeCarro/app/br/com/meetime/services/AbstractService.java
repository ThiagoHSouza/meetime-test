package br.com.meetime.services;

import java.util.List;

import br.com.meetime.exceptions.MeetimeException;

public interface AbstractService<E> {

	public E create( E entity ) throws MeetimeException;
	
	public E update( E entity ) throws MeetimeException;
	
	public void remove ( Long id ) throws MeetimeException;
	
	public E findById( Long id ) throws MeetimeException;
	
	public List<E> findAll() throws MeetimeException;
}
