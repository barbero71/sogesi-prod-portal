package test.it.sogesispa.prod.web.models.jpa.bugs;

import it.sogesispa.prod.web.models.jpa.PaLinea;
import it.sogesispa.prod.web.models.jpa.PaPianificazioni;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class _20170309_filtra_lista_attivita {

	public static void main(String[] args) { 
		EntityManagerFactory entityManagerFactory;
		
		entityManagerFactory = Persistence.createEntityManagerFactory("test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		entityManager.getTransaction().begin();
		
		Query q = entityManager.createQuery(" SELECT c FROM PaPianificazioni c where 1 = 1  and c.deleted = 0  "
				+ "and (c.paMacchine.TPlant.plantId = 1)   "
				+ "and (c.paMacchine.paLinea.id = 1)  "
				+ " and (c.paMacchine.paTipiMacchina.id in (3))  order by  c.id desc ");
		 
		
		List<PaPianificazioni> lista = q.getResultList();
		
		for (PaPianificazioni paPianificazioni : lista) {
			System.out.println( paPianificazioni.getPaMacchine().getDescrizione()  );
		}
		
		System.out.println("END");
		
	}

}
