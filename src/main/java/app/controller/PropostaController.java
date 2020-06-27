package app.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.ResponseModel;
import app.entities.Proposta;
import app.repository.PropostaRepository;



//Jeferson Knop


@RestController
@RequestMapping("/propostas")
@CrossOrigin(origins = "*")

public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Proposta proposta){ 
 
		try { 
			proposta.setId(null);
			this.propostaRepository.save(proposta); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage()+ " - - - - "+ proposta.getId());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Proposta proposta){
 
		try {
 
			this.propostaRepository.save(proposta);		 
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	
		 

	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Proposta> proposta = propostaRepository.findById(id);
		if (!proposta.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {
				propostaRepository.delete(proposta.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Proposta> findById (@PathVariable Long id){
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		if (proposta == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(proposta.get());
					
	}
	
	
	@GetMapping
	public @ResponseBody List<Proposta> findAll(){
		return this.propostaRepository.findAll();		
	}


	
	@GetMapping("/{page}/{count}")
	public @ResponseBody Page<Proposta> findAll(@PathVariable int page, @PathVariable int count){
			return this.propostaRepository.findAll(PageRequest.of(page, count));
		
	}
	

	
	
	@GetMapping("/cpf/{id}")
	public @ResponseBody List<Proposta> findByCpfCliente (@PathVariable String cpf){
		return this.propostaRepository.findByCpfCliente(cpf);		
					
	}
	
}
	