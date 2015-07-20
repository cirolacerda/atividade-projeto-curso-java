package br.com.dissemine.escola.web;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.dissemine.escola.dominio.Curso;
import br.com.dissemine.escola.infraestrutura.JPAUtil;

@ManagedBean
public class CursoBean {

	private Curso curso = new Curso();

	private List<Curso> cursos;

	public CursoBean() {

		Map<String, String> parametros = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		String parametroId = parametros.get("id");

		if (parametroId != null) {
			EntityManager em = JPAUtil.getEntityManager();
			curso = em.find(Curso.class, Long.valueOf(parametroId));
			em.close();
		}

	}

	public void salvar() {

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (curso.getId() == null) {
			em.persist(curso);
		} else {
			em.merge(curso);
		}

		tx.commit();
		em.close();

		curso = new Curso();

	}

	public void excluir(Curso curso) {

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		Curso cursoGerenciado = em.merge(curso);
		em.remove(cursoGerenciado);
		tx.commit();
		em.close();
		cursos = null;

	}

	// GETTERs and SETTERs
	@SuppressWarnings("unchecked")
	public List<Curso> getCursos() {
		if (this.cursos == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("select a from Curso a", Curso.class);

			this.cursos = query.getResultList();

			em.close();
		}

		return cursos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
