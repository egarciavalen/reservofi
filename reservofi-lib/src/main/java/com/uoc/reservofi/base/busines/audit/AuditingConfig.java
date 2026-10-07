package com.uoc.reservofi.base.busines.audit;

import com.uoc.reservofi.base.busines.model.BaseEntidad;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	@Bean
	@Profile("!test") // Para la aplicación normal
	public AuditorAware<BaseEntidad> projectAuditorProvider() {
		return new AuditorAwareImpl();
	}

	@Bean
	@Profile("test") // Para la configuración de tests
	public AuditorAware<String> projectAuditorProviderTest() {
		return () -> Optional.of("adminTest");
	}

}
