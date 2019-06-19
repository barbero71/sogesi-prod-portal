package test.it.sogesispa.prod.web.models.jpa;

import java.util.List;

import it.sogesispa.prod.web.models.jpa.PaAttivita;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class _02_listaAttivita {


	public static void main(String[] args) { 
		EntityManagerFactory entityManagerFactory;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		entityManager.getTransaction().begin();
		
		Query q = entityManager.createQuery("select a from PaAttivita a join a.paMacchine m  where  m.TPlant.plantId =:plantId");
		
		q.setParameter("plantId", 1l); 
		
		List<PaAttivita> lista = q.getResultList();
		
		for (PaAttivita paAttivita : lista) {
			System.out.println( paAttivita.getDescrizioneIntt() );
		}
	}



}
