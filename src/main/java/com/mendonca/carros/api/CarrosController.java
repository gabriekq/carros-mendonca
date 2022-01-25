package com.mendonca.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mendonca.carros.domain.Carro;
import com.mendonca.carros.domain.CarroService;
import com.mendonca.carros.dto.CarroDTO;

import javassist.tools.rmi.ObjectNotFoundException;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

	@Autowired
	private CarroService service;
	
	
	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() {
	
		return ResponseEntity.ok(service.getCarros());
		//	return new ResponseEntity<Iterable<Carro>>(service.getCarros(),HttpStatus.OK )  ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) throws ObjectNotFoundException {
		
	CarroDTO Optionalcarro	 = service.getCarrosById(id);
		
	return ResponseEntity.ok(Optionalcarro);
			
	}
	
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
		
		
	}
	
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity post(@RequestBody Carro carro) {
	 
		
	
	 CarroDTO objCarro =   service.insert(carro);
	 URI location = getURi(objCarro.getId());
	 return ResponseEntity.created(location).build();
	
	}
	
  private URI getURi(Long id){
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro   ) {
		CarroDTO carroDto = service.update(carro,id);
		
		return carroDto != null ? ResponseEntity.ok(carroDto) :ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id  ) {
		
	    service.delete(id);
		return  ResponseEntity.ok().build();
	}
	
	
	
	
}
