package test.it.sogesispa.prod.web.models.jpa;

import it.sogesispa.prod.web.models.jpa.PaAttivita;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _01_LoadAttivita {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory; 

		entityManagerFactory = Persistence.createEntityManagerFactory("test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 

		entityManager.getTransaction().begin();
		
		PaAttivita paAttivita = entityManager.find(PaAttivita.class, 1l); 

		System.out.println( paAttivita.getDescrizioneIntt() );
		System.out.println( paAttivita.getPaImpianti().getDescrizione() );
		System.out.println( paAttivita.getPaMacchine().getPaTipiMacchina().getDescrizione() );
	}

}
