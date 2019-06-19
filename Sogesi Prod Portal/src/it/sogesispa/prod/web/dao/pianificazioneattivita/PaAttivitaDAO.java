package it.sogesispa.prod.web.dao.pianificazioneattivita;

import java.util.List;

import it.sogesispa.prod.web.models.jpa.PaAttivita; 

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component("paAttivitaDao")
public class PaAttivitaDAO {
	
	@SuppressWarnings("unchecked")
	public List<PaAttivita> find(long plantId){ 
		EntityManagerFactory entityManagerFactory;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		entityManager.getTransaction().begin();
		
		Query q = entityManager.createQuery("select a from PaAttivita a join a.paMacchine m  where  m.TPlant.plantId =:plantId");
		
		q.setParameter("plantId", plantId); 
		
		return q.getResultList();
	}
	
}
