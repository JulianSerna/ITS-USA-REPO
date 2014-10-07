package org.wsbn.servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.wsbn.persistence.PersistenceManager;

@WebListener
public class EmfServlet implements ServletContextListener {
	
  public void contextInitialized(ServletContextEvent event) 
  {
	  System.out.println("SERVLET LISTENER STARTED ............");
	  
  
  }

  public void contextDestroyed(ServletContextEvent event) {
	  PersistenceManager.closeEntityManagerFactory();
	  
	  System.out.println("SERVLET LISTENER DESTROYED ............");
  }


}