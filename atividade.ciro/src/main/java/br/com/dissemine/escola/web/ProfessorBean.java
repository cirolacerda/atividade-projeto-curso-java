package br.com.dissemine.escola.web;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.dissemine.escola.dominio.Professor;
import br.com.dissemine.escola.infraestrutura.JPAUtil;

@ManagedBean
public class ProfessorBean {

	private Professor professor = new Professor();

	private List<Professor> professores;

	public ProfessorBean() {

		Map<String, String> parametros = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		String parametroId = parametros.get("id");

		if (parametroId != null) {
			EntityManager em = JPAUtil.getEntityManager();
			professor = em.find(Professor.class, Long.valueOf(parametroId));
			em.close();
		}

	}

	public void salvar() {

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (professor.getId() == null) {
			em.persist(professor);
		} else {
			em.merge(professor);
		}

		tx.commit();
		em.close();

		professor = new Professor();

	}

	public void excluir(Professor professor) {

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		Professor professorGerenciado = em.merge(professor);
		em.remove(professorGerenciado);
		tx.commit();
		em.close();
		professores = null;

	}

	// GETTERs and SETTERs
	@SuppressWarnings("unchecked")
	public List<Professor> getProfessores() {
		if (this.professores == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("select a from Professor a",
					Professor.class);

			this.professores = query.getResultList();

			em.close();
		}

		return professores;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

}
