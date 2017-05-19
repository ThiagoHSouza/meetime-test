package br.com.meetime.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.meetime.enums.ColorCar;
import br.com.meetime.util.jsonConverter.ArrayColorCarSerializer;
import br.com.meetime.util.jsonConverter.ColorCarDeserializer;
import br.com.meetime.util.jsonConverter.ColorCarSerializer;

/**
 * Entity Car
 * @author thiago_souza07
 *
 */
public class Car extends AbstractModel{
	
	private Long id;
	
	private String model;
	
	private Integer year;

	@JsonSerialize(using = ColorCarSerializer.class)
	@JsonDeserialize(using = ColorCarDeserializer.class)
	private ColorCar color;
	
	/**
	 * Id of person registered in pipedriver.
	 */
	private Long idPerson;
	
	public Car() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public ColorCar getColor() {
		return color;
	}

	public void setColor(ColorCar color) {
		this.color = color;
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", model=" + model + ", year=" + year + ", color=" + color + ", idPerson=" + idPerson
				+ "]";
	}
	
	
}
