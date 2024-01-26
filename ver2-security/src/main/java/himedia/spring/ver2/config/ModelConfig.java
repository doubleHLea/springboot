package himedia.spring.ver2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
	
	// Model Mapper
	// DTO <-> Entity type change
	@Bean
	protected ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
