package test.it.sogesispa.prod.web.models.jpa;

import it.sogesispa.prod.web.models.jpa.PaAttivita;
import it.sogesispa.prod.web.models.jpa.TPlant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _03_TPlant {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory; 

		entityManagerFactory = Persistence.createEntityManagerFactory("test"); 
		   
		EntityManager entityManager = entityManagerFactory.createEntityManager();   

		entityManager.getTransaction().begin();
		
		TPlant tPlant = entityManager.find(TPlant.class, 1l); 

		System.out.println( tPlant.getPlantDesc() ) ;


		
	}

}
