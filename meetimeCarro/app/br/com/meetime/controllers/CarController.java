package br.com.meetime.controllers;

import static play.libs.Json.toJson;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.meetime.exceptions.MeetimeException;
import br.com.meetime.exceptions.RepositoryError;
import br.com.meetime.models.Car;
import br.com.meetime.services.CarService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * CarController 
 * @author thiago_souza07
 *
 * @param <E> Model entity;
 * @param <Service> Service extends AbstractService
 */
public class CarController extends Controller{
	
	/**
	 * Inject service.
	 */
	@Inject
	private CarService carService;
	
	/**
	 * Provider Logger as logger;
	 */
	protected final Logger logger = Logger.getLogger(getClass().getName());
	
	
	/**
	 * Creates a new record.
	 * @return
	 */
    public Result create() {
    	try {
    		JsonNode json = request().body().asJson();
    		Car car = Json.fromJson(json, Car.class);
    		return respose(carService.create(car));
		} catch (MeetimeException | RepositoryError e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return badRequest(e.getMessage());
		}
    }
    
    /**
     * Changes a record.
     * @return
     */
    public Result update() {
    	try {
    		JsonNode json = request().body().asJson();
    		Car car = Json.fromJson(json, Car.class);
    		return respose(carService.update(car));
		} catch (MeetimeException | RepositoryError e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return badRequest(e.getMessage());
		}
    }
    
    /**
     * Search for a record by the id.
     * @return
     */
    public Result findById(Long id) {
    	try {
    		return respose(carService.findById(id));			
		} catch (RepositoryError e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return badRequest(e.getMessage());
		}
    }
    
    /**
     * Search for all records of type
     * @return
     */
    public Result findAll() {
    	try {
    		return respose(carService.findAll());
		} catch (RepositoryError e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return badRequest(e.getMessage());
		}
    }
    
    /**
     * Delete a record by the id
     * @param id
     * @return
     */
    public Result delete(Long id) {
    	try {
    		carService.remove(id);
    		return respose();
		} catch (RepositoryError e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return badRequest(e.getMessage());
		}
    }
    
    private Result respose() {
		return noContent();
	}

	private Result respose(Object car){
    	return ok(toJson(car)).as(MediaType.APPLICATION_JSON);
    }
	
	
	
    
        
    
}
