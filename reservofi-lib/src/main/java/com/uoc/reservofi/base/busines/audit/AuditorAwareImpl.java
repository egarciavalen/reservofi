package com.uoc.reservofi.base.busines.audit;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<BaseEntidadActualizable> {

	@Override
	@NullMarked
	public Optional<BaseEntidadActualizable> getCurrentAuditor() {
		// Devuelve el usuario autenticado para guardar en base de datos
		return Optional.of(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal)
				.map(BaseEntidadActualizable.class::cast);
	}
}
