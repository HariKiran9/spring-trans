package com.hk.trans;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Initial bootStrapper and it seeds data into the neo4J database.
 *
 * @author Hari Kiran
 */

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
//@PropertySource(ignoreResourceNotFound = true, value = {"file:${MLM_CONFIG_HOME}/application.properties"})
public class TransApplication extends SpringBootServletInitializer implements CommandLineRunner {

	/**
	 * Reference to logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(TransApplication.class);

	/**
	 * main method used during development to start the spring boot application.
	 *
	 * @param args arguments to the main method
	 * @throws Exception thrown for any unknown conditions
	 */
	public static void main(final String[] args) throws Exception {

		LOG.info("this is a info message");
		LOG.debug("this is a debug message");
		LOG.warn("this is a warn message");
		LOG.error("this is a error message");

		final SpringApplication application = new SpringApplication(TransApplication.class);
		final Properties properties = new Properties();
		application.setBannerMode(Mode.CONSOLE);
		application.setDefaultProperties(properties);

		application.run();
	}

	@Override
	public void run(final String... args) throws Exception {
		// CALL apoc.date.format(timestamp(),"ms","dd.MM.yyyy")
		LOG.info("Executing boot scripts ");
	}
}
