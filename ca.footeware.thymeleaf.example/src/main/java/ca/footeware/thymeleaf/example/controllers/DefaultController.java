/**
 * 
 */
package ca.footeware.thymeleaf.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author footeware.ca
 *
 */
@Controller
public class DefaultController {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultController.class);

	@GetMapping("/")
	public String getGreeting(Model model) {
		model.addAttribute("greeting", "Hello World!");
		LOG.info("Returning greeting to index");
		return "index";
	}

	@GetMapping("/weather")
	@ResponseBody
	public String getWeather() {
		LOG.info("Returning 'snow'.");
		return "Snow!";
	}

	@PostMapping("/weather/{forecast}")
	@ResponseBody
	public String setWeather(@PathVariable("forecast") String forecast) {
		LOG.info("Received " + forecast);
		return forecast;
	}

	@DeleteMapping("/weather/{forecast}")
	@ResponseBody
	public String deleteWeather(@PathVariable("forecast") String forecast) {
		LOG.info("Deleting " + forecast);
		return forecast;
	}
}
