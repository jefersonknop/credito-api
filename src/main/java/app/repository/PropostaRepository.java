package app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Proposta;



public interface PropostaRepository extends JpaRepository<Proposta, Long> {	
	
	@Query(value = "SELECT * FROM PROPOSTA P JOIN CLIENTE C ON P.CLINTE = C.ID WHERE C.CPF =?1", nativeQuery = true)	  
	List<Proposta> findByCpfCliente(String cpf);
	
	
}