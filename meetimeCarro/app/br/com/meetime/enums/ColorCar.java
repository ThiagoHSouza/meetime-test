package br.com.meetime.enums;

/**
 * Provides the possible car color.
 * @author thiago_souza07
 *
 */
public enum ColorCar {
	WHITE("Branco"),
	GREEN("Verde"),
	BLACK("Preto");
	
	String description;
	ColorCar(String color){
		this.description = color;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public static ColorCar get(String color){
		for (ColorCar colorCar : ColorCar.values()) {
			if(color.equals(colorCar.name())){
				return colorCar;
			}
		}
		return null;
	}

}
