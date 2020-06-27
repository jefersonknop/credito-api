package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Proposta {
	@Id
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private Long id;	
	
	@JoinColumn(name = "cliente", referencedColumnName = "id")
	@ManyToOne
    private Cliente cliente;
	
	@Size(max = 100)
	@Column(name = "ANALISE")
	private String analise;
	
	@Column(name = "LIMITE_MINIMO")
	private double limite_minimo;
	
	@Column(name = "LIMITE_MAXIMO")
	private double limite_maximo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getAnalise() {
		return analise;
	}

	public void setAnalise(String analise) {
		this.analise = analise;
	}

	public double getLimite_minimo() {
		return limite_minimo;
	}

	public void setLimite_minimo(double limite_minimo) {
		this.limite_minimo = limite_minimo;
	}

	public double getLimite_maximo() {
		return limite_maximo;
	}

	public void setLimite_maximo(double limite_maximo) {
		this.limite_maximo = limite_maximo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposta other = (Proposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	


}
