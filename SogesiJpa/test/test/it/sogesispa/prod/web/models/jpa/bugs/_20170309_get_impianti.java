package test.it.sogesispa.prod.web.models.jpa.bugs;

import it.sogesispa.prod.web.models.jpa.PaImpianti;
import it.sogesispa.prod.web.models.jpa.PaLinea;
import it.sogesispa.prod.web.models.jpa.PaPianificazioni;
import it.sogesispa.prod.web.models.jpa.PaTipiMacchina;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class _20170309_get_impianti {
	

	public static void mainAAAAAAAAAAAAA(String[] args) { 
		EntityManagerFactory entityManagerFactory;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("production_test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		entityManager.getTransaction().begin();
		String query = "select tm from PaTipiMacchina tm"
				+ " JOIN tm.paMacchines m   "
				+ " where m.id = "
				+ "1002";
		
		Query q = entityManager.createQuery(query);
		  
		List<PaTipiMacchina> lista = q.getResultList();
		
		for (PaTipiMacchina paTipiMacchina : lista) {
			System.out.println( paTipiMacchina.getDescrizione() );
		}
		
		System.out.println("END");
		
	}

	public static void main (String[] args) { 
		EntityManagerFactory entityManagerFactory;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("production_test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		entityManager.getTransaction().begin();
		String query = "select d from PaImpianti d "
				+ "JOIN d.paImpiantoTMacchinas e "
				+ " JOIN e.paTipiMacchina tm"
				+ " JOIN tm.paMacchines m   "
				+ " where m.id = "
				+ "1002";
		
		Query q = entityManager.createQuery(query);
		  
		List<PaImpianti> lista = q.getResultList();
		
		for (PaImpianti paImpianti : lista) {
			System.out.println( paImpianti.getDescrizione() );
		}
		
		System.out.println("END");
		
	}

}
