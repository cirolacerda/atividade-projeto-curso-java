package br.com.dissemine.escola.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Professor {
	
	@Id
	@SequenceGenerator(name="professorGenerator", sequenceName="professorSequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="professorGenerator")
	private Long id;
	
	
	@Column(length=100, nullable=false)
	private String nome;


	

	// GETTERs and SETTERs
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
