package br.com.meetime.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import br.com.meetime.models.Car;

/**
 * Car Persistence
 * @author thiago_souza07
 *
 */
@Singleton
public class CarPersistence extends AbstractRepository<Car>{

	private static List<Car> listCar = new ArrayList<>();
	private final AtomicInteger atomicCounter = new AtomicInteger(1);
	
	@Override
	protected List<Car> getBase() {
		return listCar;
	}

	@Override
	protected Long getIndex() {
		return Long.valueOf(atomicCounter.getAndIncrement());
	}

}
