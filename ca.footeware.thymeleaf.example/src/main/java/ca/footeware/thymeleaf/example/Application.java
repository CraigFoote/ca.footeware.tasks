package ca.footeware.thymeleaf.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("Application starting...");
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	protected void postConstruct() {
		LOG.info("Application started");
	}

	@PreDestroy
	protected void preDestroy() {
		LOG.info("Application stopped.");
	}
}
