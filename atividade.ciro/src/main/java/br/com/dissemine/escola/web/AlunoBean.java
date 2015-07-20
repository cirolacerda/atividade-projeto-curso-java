package br.com.dissemine.escola.web;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.dissemine.escola.dominio.Aluno;
import br.com.dissemine.escola.infraestrutura.JPAUtil;


@ManagedBean
public class AlunoBean {
	
	private Aluno aluno = new Aluno();
	
	private List<Aluno> alunos;
	
	public AlunoBean(){
		
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		String parametroId = parametros.get("id");
		
		if( parametroId != null){
			EntityManager em = JPAUtil.getEntityManager();
			aluno = em.find(Aluno.class, Long.valueOf(parametroId));
			em.close();
		}
		
	}
	
	public void salvar(){
		
		
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		if(aluno.getId() == null){
			em.persist(aluno);
		}else {
			em.merge(aluno);
		}
		
		tx.commit();
		em.close();
		
		aluno = new Aluno();
		
		 	
	}
	
	public void excluir(Aluno aluno){
		
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Aluno alunoGerenciado = em.merge(aluno);
		em.remove(alunoGerenciado);
		tx.commit();
		em.close();
		alunos = null;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> getAlunos(){
		if(this.alunos == null){
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("select a from Aluno a", Aluno.class);
		
		
		this.alunos = query.getResultList();
		
		em.close();
		}
		
		return alunos;
	}

	
	// GETTERs and SETTERs
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	

}
