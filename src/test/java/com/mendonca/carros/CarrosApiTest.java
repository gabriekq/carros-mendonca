package com.mendonca.carros;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;



import com.mendonca.carros.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosApiTest {

	@Autowired
	protected TestRestTemplate rest;
	
	private ResponseEntity<CarroDTO> getCarro(String url){
		
		return rest.withBasicAuth("admin", "123").getForEntity(url, CarroDTO.class);
		
	}
	
	
	private ResponseEntity<List<CarroDTO>> getCarros(String url){
		
		return rest.withBasicAuth("admin", "123").exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CarroDTO>>() {
		});
	}
	
	
	@Test
	public void testLista() {
	List<CarroDTO>	carros = getCarros("/api/v1/carros").getBody();
	assertNotNull(carros);
	assertEquals(30, carros.size());	
	}
	
	@Test
	public void testGetOk() {
		ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		
		CarroDTO carro = response.getBody();
		assertEquals("Ferrari FF",carro.getNome());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
