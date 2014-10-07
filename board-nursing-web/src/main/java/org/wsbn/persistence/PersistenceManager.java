package org.wsbn.persistence;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@ApplicationScoped
public class PersistenceManager
{
	// STATE
	private static EntityManagerFactory emFactory;

	// CONSTRUCTOR(S)
	static {
		emFactory = Persistence.createEntityManagerFactory("jpa-one");
	}

	// SETTERS-GETTERS
	public static EntityManager createEntityManager() 
	{
	  EntityManager oResponse = null;
	  try
	  {
		  oResponse = emFactory.createEntityManager();
	  }
	  catch(Exception e)
	  {
		  
			 emFactory = Persistence.createEntityManagerFactory("jpa-one");
			 oResponse = emFactory.createEntityManager();
	  }
	  
	  return oResponse;
		  
  }
	@PreDestroy	
	public static void closeEntityManagerFactory()
	{
		emFactory.close();
		System.out.println("ENTITY MANAGER FACTORY DESTROYED .........");
	}
	
	public static void reopenEntityManagerFactory()
	{
		emFactory = Persistence.createEntityManagerFactory("jpa-one");
	}

}