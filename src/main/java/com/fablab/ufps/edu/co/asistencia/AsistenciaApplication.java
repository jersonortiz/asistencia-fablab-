package com.fablab.ufps.edu.co.asistencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/**
 * Main class for the Asistencia Spring Boot application.
 *
 * <p>
 * This class contains the main method which serves as the entry point for the
 * Spring Boot application.</p>
 *
 * @authorjerson
 */
@SpringBootApplication
public class AsistenciaApplication {

    /**
     * Main method that runs the Spring Boot application.
     *
     * <p>
     * SpringApplication.run() method is used to launch the application.</p>
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AsistenciaApplication.class, args);
    }

}
