package br.com.meetime.templates;

import br.com.meetime.models.Car;
import br.com.meetime.services.CarService;

import java.time.LocalDate;

import br.com.meetime.enums.ColorCar;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CarTemplate implements TemplateLoader {
	private static final Long COD_CARRO = 1L;
	
	public static enum Template {
		VALID_CAR_GOL,
		VALID_CAR_PALIO,
		WITHOUT_COLOR,
		WITHOUT_MODEL,
		WITHOUT_YEAR,
		WITHOUT_ID_PERSON, 
		WITHOUT_ID,
		OVER_THIRTY_YEAR
	}
	
	@Override
	public void load() {
		Fixture.of(Car.class).addTemplate(Template.VALID_CAR_GOL.name(), new Rule() {{
			add("id", COD_CARRO);
			add("color", ColorCar.BLACK);
			add("model", "Gol");
			add("year", 2010);
			add("idPerson", 1L);
		}});
		
		otherValidCar();
		toWithoutAttrTest();
		otherInvalidCar();
	}
	
	private void otherInvalidCar(){
		Fixture.of(Car.class).addTemplate(Template.OVER_THIRTY_YEAR.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("year", LocalDate.now().minusYears(Long.sum(CarService.MAXIMUM_YEAR_A_CAR, 1L)).getYear());
		}});
	}
	
	private void otherValidCar(){
		Fixture.of(Car.class).addTemplate(Template.VALID_CAR_PALIO.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("model", "Palio");
		}});
	}
	
	private void toWithoutAttrTest(){
		Fixture.of(Car.class).addTemplate(Template.WITHOUT_ID.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("id", null);
		}});
		
		Fixture.of(Car.class).addTemplate(Template.WITHOUT_COLOR.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("color", null);
		}});
		
		Fixture.of(Car.class).addTemplate(Template.WITHOUT_MODEL.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("model", null);
		}});
		
		Fixture.of(Car.class).addTemplate(Template.WITHOUT_YEAR.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("year", null);
		}});
		
		Fixture.of(Car.class).addTemplate(Template.WITHOUT_ID_PERSON.name()).inherits(Template.VALID_CAR_GOL.name(), new Rule(){{
			add("idPerson", null);
		}});
	}

}
