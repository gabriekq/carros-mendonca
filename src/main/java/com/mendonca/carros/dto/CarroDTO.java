package com.mendonca.carros.dto;

import org.modelmapper.ModelMapper;

import com.mendonca.carros.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	private Long id;
	private String nome;
	private String tipo;
	
	
	public static CarroDTO create(Carro carro) {
	ModelMapper modelMapper = new ModelMapper();
	return modelMapper.map(carro, CarroDTO.class);
		
	}
	
	
	
	
}
