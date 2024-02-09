package com.microservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import jakarta.annotation.PostConstruct;

@Configuration
public class SpringMongoConfig{
	
	@Autowired
	private MappingMongoConverter mappingMongoConverter;

	@PostConstruct
	public void configureMappingMongoConverter() {
	    this.mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
	}	
}
