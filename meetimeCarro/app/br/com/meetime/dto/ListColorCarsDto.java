package br.com.meetime.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.meetime.enums.ColorCar;
import br.com.meetime.util.jsonConverter.ArrayColorCarSerializer;

public class ListColorCarsDto {
	
	@JsonSerialize(using = ArrayColorCarSerializer.class)
	private ColorCar[] enums;
	
	public ListColorCarsDto(){
		enums = ColorCar.values();
	}
	
	public ColorCar[] getEnums() {
		return enums;
	}

}
