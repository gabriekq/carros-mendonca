package com.mendonca.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mendonca.carros.dto.CarroDTO;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {

    @Autowired	
	private CarroRepository carroRepository;
	
    
    public List<CarroDTO> getCarros(){
    	List<Carro> carros = carroRepository.findAll();
    	
    List<CarroDTO> carrosDTO =	carros.stream().map(carro -> CarroDTO.create(carro)).collect(Collectors.toList());
    	
    	
    	return carrosDTO;
    }
    
    public CarroDTO getCarrosById(Long id) throws ObjectNotFoundException  {
		
    	 Optional<Carro> carro = carroRepository.findById(id);
         return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
	}
    
    	
     public List<CarroDTO> getCarrosByTipo(String tipo) {
	
		return carroRepository.findByTipo(tipo).stream().map(carro ->  CarroDTO.create(carro)).collect(Collectors.toList());
	}
    	
    public CarroDTO insert(Carro carro) {
     
    	Assert.isNull(carro.getId(), "nao foi possivel inserir");
    	return	 CarroDTO.create( carroRepository.save(carro));
		
	}
    	
    public CarroDTO update(Carro carro, Long id) {
		
    	Assert.notNull(id,"nao foi possivel atualizar o registro");
    	
       Optional<Carro> optional = 	carroRepository.findById(id);
    	
       if(optional.isPresent()) {
    	   Carro carroDb = optional.get();
    	   
    	   carroDb.setNome(carro.getNome());
    	   carroDb.setTipo( carro.getTipo());
    	   System.out.println("Carro id "+carroDb.getId());
    	   
    	   carroRepository.save(carroDb);
    	   return  CarroDTO.create(carroDb);
       }else {
    	   //throw new RuntimeException("nao foi possivel atualizar o registro");
    	   return null;
       }
       
       
    	
		
	}	
	
    public void delete(Long id) {
        carroRepository.deleteById(id);
	}
    
    
    
	public List<Carro> getCarrosFake(){
		List<Carro> carros = new ArrayList<Carro>();
		//carros.add(new Carro(1L, "fusca")  );
		//carros.add(new Carro(2L, "gol")  );
	//	carros.add(new Carro(3L, "mustang")  );
		return carros;
		
		
	}

	

	









	
	
}
