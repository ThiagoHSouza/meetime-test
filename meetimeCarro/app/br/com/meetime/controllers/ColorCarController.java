package br.com.meetime.controllers;

import static play.libs.Json.toJson;

import br.com.meetime.dto.ListColorCarsDto;
import play.mvc.Controller;
import play.mvc.Result;

public class ColorCarController extends Controller {

	public Result listColors(){
		return ok(toJson( new ListColorCarsDto())).as(MediaType.APPLICATION_JSON);
	}
}
