package com.example.cc;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for model mapper
 *
 */
@Configuration
public class CreditCardConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
