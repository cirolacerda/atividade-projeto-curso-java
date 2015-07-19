package br.com.dissemine.escola.infraestrutura;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola-pu");;

	public static EntityManager getEntityManager() {

		return emf.createEntityManager();
	}
	
	public static void closeEntityManagerFactory(){
		
		emf.close();
		
	}

}
