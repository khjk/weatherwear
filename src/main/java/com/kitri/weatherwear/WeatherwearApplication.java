package com.kitri.weatherwear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class WeatherwearApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherwearApplication.class, args);
	}

}
