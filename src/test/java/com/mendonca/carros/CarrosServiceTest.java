package com.mendonca.carros;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mendonca.carros.domain.Carro;
import com.mendonca.carros.domain.CarroService;
import com.mendonca.carros.dto.CarroDTO;


@SpringBootTest
class CarrosServiceTest {

     @Autowired	
	private CarroService carroService;
	
	
	@Test
	void testSave() {
		
		Carro carro = new Carro();
		carro.setNome("Gurgel jamal");
		carro.setTipo("classico");
		
	  CarroDTO carroDto =	carroService.insert(carro); 
	  assertNotNull(carroDto);

	  Long id  = carroDto.getId();  
	  assertNotNull(id);
	  
	  // buscar carrp
	//Optional<CarroDTO> op =   carroService.getCarrosById(id);
	//assertTrue(op.isPresent());
	
	//carroDto= op.get();
	assertEquals("Gurgel jamal", carroDto.getNome());
	assertEquals("classico", carroDto.getTipo());
	
	// deletar obj
	carroService.delete(id);
	
	// verificar se o carro foi deletado
	
	//assertFalse( carroService.getCarrosById(id).isPresent() );

	}
	
	
	@Test
	void test2() {
		List<CarroDTO> carros = carroService.getCarros();
		assertEquals(30, carros.size());
	}
	
	@Test
	void testget() {
	//	Optional<CarroDTO> carro = carroService.getCarrosById(11L);
		//assertTrue(carro.isPresent());
		
		//CarroDTO carroDTO = carro.get();
		//assertEquals("Ferrari FF",carroDTO.getNome());
		

	}
	
	@Test
	void testByTipo() {

		assertEquals(10, carroService.getCarrosByTipo("esportivos").size());
		assertEquals(10, carroService.getCarrosByTipo("luxo").size());
	}
	


}
