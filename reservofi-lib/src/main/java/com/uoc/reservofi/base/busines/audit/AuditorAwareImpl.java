package com.uoc.reservofi.base.busines.audit;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	@NullMarked
	public Optional<String> getCurrentAuditor() {
		// Devuelve el nombre del usuario autenticado (anonymousUser si no está autenticado)
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(Authentication::getName);
	}
}
