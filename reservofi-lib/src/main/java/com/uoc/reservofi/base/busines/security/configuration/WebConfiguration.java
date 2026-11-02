package com.uoc.reservofi.base.busines.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	// Para poder obtener las imágenes por URL
	// Sin esto, va a dar un error 500 al intentar recuperarlas

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
				.addResourceHandler("/reservofi-lib/resources/images/**") // Ubicación de las imágenes
				.addResourceLocations("file:./reservofi-lib/resources/images/"); // Se busca desde el sistema de archivos, en el directorio de trabajo
	}
}