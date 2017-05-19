package br.com.meetime.service;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import br.com.meetime.exceptions.CarValidateException;
import br.com.meetime.models.Car;
import br.com.meetime.persistence.CarPersistence;
import br.com.meetime.services.CarService;
import br.com.meetime.templates.CarTemplate.Template;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * Class responsible for performing the carService test.
 * @author thiago_souza07
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	/**
	 * Template arrangement package.
	 * It is used by the FixtureFactory framework so that it can provide the templates
	 */
	private static final String PATH_TEMPLATES = "br.com.meetime.templates";
	
	/**
	 * METHOD_*:String => Name of the private method to be invoked. 
	 */
	private static final String METHOD_VALIDATE_CAR = "validateCar";

	@Mock
	private CarPersistence carPersistence;

	@InjectMocks
	private CarService carService = new CarService();

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates(PATH_TEMPLATES);
	}

	/**
	 * Test the carService.create method on a success case.
	 * @throws CarValidateException
	 */
	@Test
	public void createSuccess() throws CarValidateException {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_ID.name());
		Car baseCar = Fixture.from(Car.class).gimme(Template.VALID_CAR_GOL.name());
		Mockito.when(carPersistence.create(Matchers.any())).thenReturn(baseCar);
		Car returnCar = carService.create(inCar);

		assertEquals(Long.valueOf(returnCar.getId()), Long.valueOf(baseCar.getId()));
	}
	
	/**
	 * Test the carService.update method on a success case.
	 * @throws CarValidateException
	 */
	@Test
	public void updateSuccess() throws CarValidateException {
		Car inCar = Fixture.from(Car.class).gimme(Template.VALID_CAR_PALIO.name());
		Car baseCar = Fixture.from(Car.class).gimme(Template.VALID_CAR_GOL.name());
		Mockito.when(carPersistence.create(Matchers.any())).thenReturn(baseCar);
		Car returnCar = carService.create(inCar);

		assertEquals(returnCar.getModel(), baseCar.getModel());
	}

	/**
	 * Test the validateCar private method with null object Car.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void createNull() throws Exception {
		Car inCar = null;
		
		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}
	
	
	/**
	 * Test the validateCar private method without Car id and with a false validation flag.
	 * @throws Exception
	 */
	@Test
	public void validateCarWithoutValidateIdAndId() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_ID.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}
	
	/**
	 * Test the validateCar private method without Car id but with a true validation flag.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void validateCarValidateIdWithoutId() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_ID.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, true);
	}

	/**
	 * Test the validateCar private method without Car model.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void validateCarWithoutModel() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_MODEL.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}

	/**
	 * Test the validateCar private method without Car color.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void validateCarWithoutColor() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_COLOR.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}

	/**
	 * Test the validateCar private method without Car year.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void validateCarWithoutYear() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_YEAR.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}

	/**
	 * Test the validateCar private method without Car person id.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void validateCarWithoutIdPerson() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.WITHOUT_ID_PERSON.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}

	/**
	 * Test the validateCar private method with car over thirty year.
	 * @throws Exception
	 */
	@Test(expected = CarValidateException.class)
	public void validateCarOverThirtyYears() throws Exception {
		Car inCar = Fixture.from(Car.class).gimme(Template.OVER_THIRTY_YEAR.name());

		Whitebox.invokeMethod(carService, METHOD_VALIDATE_CAR, inCar, false);
	}

}
