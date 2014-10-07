package org.wsbn.bootstrap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



import org.wsbn.dto.UserDto;
import org.wsbn.persistence.PersistenceManager;

public class Main 
{
  public static void main(String[] args)    
  {
    // STATE
	EntityManagerFactory emFactory;
	//emFactory = emFactory = Persistence.createEntityManagerFactory("jpa-one");
	EntityManager oEm =  PersistenceManager.createEntityManager();
	
	UserDto user = new UserDto();
    user.setName("JulianSerna3");
    user.setPassword("Novasys1");
    
    oEm.getTransaction().begin();
    oEm.persist(user);
    oEm.getTransaction().commit();

    oEm.close();
  }
   
 
}