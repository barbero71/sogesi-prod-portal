package test.it.sogesispa.prod.web.models.jpa;

import it.sogesispa.prod.web.models.jpa.PaAttivita;
import it.sogesispa.prod.web.models.jpa.PaLinea;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class _04_load_linea_from_macchine {

	public static void main(String[] args) { 
		EntityManagerFactory entityManagerFactory;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		entityManager.getTransaction().begin();
		
		Query q = entityManager.createQuery("select distinct(l) from PaLinea l join l.paMacchinas m  where  m.TPlant.plantId =:plantId");
		
		q.setParameter("plantId", 1l); 
		
		List<PaLinea> lista = q.getResultList();
		
		for (PaLinea paLinea : lista) {
			System.out.println( paLinea.getDescrizione() );
		}
		
		
	}

}
