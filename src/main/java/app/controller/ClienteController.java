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
import app.entities.Cliente;
import app.repository.ClienteRepository;



//Jeferson Knop


@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")

public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Cliente cliente){ 
 
		try { 
			cliente.setId(null);
			this.clienteRepository.save(cliente); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage()+ " - - - - "+ cliente.getId());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Cliente cliente){
 
		try {
 
			this.clienteRepository.save(cliente);		 
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	
		 

	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {
				clienteRepository.delete(cliente.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById (@PathVariable Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if (cliente == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(cliente.get());
					
	}
	
	
	@GetMapping
	public @ResponseBody List<Cliente> findAll(){
		return this.clienteRepository.findAll();		
	}


	
	@GetMapping("/{page}/{count}")
	public @ResponseBody Page<Cliente> findAll(@PathVariable int page, @PathVariable int count){
			return this.clienteRepository.findAll(PageRequest.of(page, count));
		
	}
	

	
	
	@GetMapping("/cpf/{id}")
	public ResponseEntity<Cliente> findByCpf (@PathVariable String cpf){
		Cliente cliente = clienteRepository.findByCpf(cpf);
		
		if (cliente == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(cliente);
					
	}
	
}
	