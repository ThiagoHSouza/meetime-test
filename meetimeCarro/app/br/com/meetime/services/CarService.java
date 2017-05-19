package br.com.meetime.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import br.com.meetime.enums.ColorCar;
import br.com.meetime.exceptions.CarValidateException;
import br.com.meetime.exceptions.YearValidateException;
import br.com.meetime.models.Car;
import br.com.meetime.persistence.CarPersistence;
import play.libs.Json;

/**
 * Car service.
 * @author thiago_souza07
 *
 */
public class CarService implements AbstractService<Car> {
	
	@Inject
	private CarPersistence carPersistence;
	
	/**
	 * Maximum age of the car.
	 */
	public static final Integer MAXIMUM_YEAR_A_CAR = 30;
	
	/**
	 * Error messages.
	 */
	private static final String MSG_ERROR_CAR_NULL = "Favor informar os dados do veículo.";
	private static final String MSG_ERROR_ID_NULL = "Favor informar o id do objeto.";
	private static final String MSG_ERROR_MODEL_NULL = "Favor informar a modelo do veículo.";
	private static final String MSG_ERROR_COLOR_NULL = "Favor informar a cor do veículo.";
	private static final String MSG_ERROR_ID_PERSON_NULL = "Favor informar a pessoa responsável pelo veículo.";
	private static final String MSG_ERROR_OLD_YEAR = "O veículo deve não pode ter mais de 30 anos.";
	
	@Override
	public Car create(Car car) throws CarValidateException {
		validateCar(car, false);
		return carPersistence.create(car);
	}

	@Override
	public Car update(Car car) throws CarValidateException{
		validateCar(car, true);
		return carPersistence.update(car);			
	}

	@Override
	public void remove(Long id){
		carPersistence.remove(id);
	}

	@Override
	public Car findById(Long id){
		return carPersistence.findById(id);
	}

	@Override
	public List<Car> findAll(){
		return carPersistence.findAll();
	}
	
	private void validateCar(Car car, Boolean validateId) throws CarValidateException{
		try {
			Objects.requireNonNull(car, MSG_ERROR_CAR_NULL);
			if(validateId){
				Objects.requireNonNull(car.getId(), MSG_ERROR_ID_NULL);
			}
			Objects.requireNonNull(car.getModel(), MSG_ERROR_MODEL_NULL);
			Objects.requireNonNull(car.getColor(), MSG_ERROR_COLOR_NULL);
			Objects.requireNonNull(car.getIdPerson(), MSG_ERROR_ID_PERSON_NULL);
			validateYearCar(car.getYear());
			
		} catch (NullPointerException | YearValidateException e) {
			throw new CarValidateException(e.getMessage(), e);
		}
	}

	
	private void validateYearCar(Integer year) throws YearValidateException{
		LocalDate nowDate = LocalDate.now();
		if(year < nowDate.minusYears(MAXIMUM_YEAR_A_CAR).getYear()){
			throw new YearValidateException(MSG_ERROR_OLD_YEAR);
		}
	}
	
}
