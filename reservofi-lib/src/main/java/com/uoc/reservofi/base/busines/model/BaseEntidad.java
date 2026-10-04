package com.uoc.reservofi.base.busines.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass // Permite que las entidades hijas hereden los atributos
@Getter
@Setter
public abstract class BaseEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

}
