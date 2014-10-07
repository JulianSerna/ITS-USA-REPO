package org.wsbn.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.ProgramDto;

public class ProgramsDao implements Serializable

{	
	private static final long serialVersionUID = 5408819800807660897L;
	
	// COLLABORATORS
	
	
	// CONSTRUCTOR(S)
	public ProgramsDao()
	{
		
		
	}
	
	
	@SuppressWarnings("finally")
	public synchronized ProgramDto addEntity(ProgramDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		ProgramDto oResponse = null;
		
		
		try
		{
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oEm.persist(oDto);	
			oResponse = oDto;
		
		oTrans.commit();
		}
		catch(Exception e)
		{
			oTrans.rollback();
		}
		
		
		finally
		{
			oEm.close();
			
		}
		
		return oResponse;
	
	}
	
	
	public synchronized ProgramDto disableEntity(ProgramDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		ProgramDto oResponse = null;
		ProgramDto oDto = new ProgramDto();
				
		oDto = this.find(pDto);
		oDto.setDisabled(true);
				
		try
		{
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oResponse = oEm.merge(oDto);
			oTrans.commit();
		}
		catch(Exception e)
		{
			oTrans.rollback();
		}
		
		
		finally
		{
			
			oEm.close();
			
		}
		
		return oResponse;
		

	}
	
	
	public synchronized ProgramDto enableEntity(ProgramDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		ProgramDto oResponse = null;
		ProgramDto oDto = new ProgramDto();
				
		oDto = this.find(pDto);
		oDto.setDisabled(false);
				
		try
		{
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oResponse = oEm.merge(oDto);
			oTrans.commit();
		}
		catch(Exception e)
		{
			oTrans.rollback();
		}
		
		
		finally
		{
			oEm.close();
			
		}
		
		return oResponse;
		

	}
	
	public synchronized ProgramDto saveEntity(ProgramDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		ProgramDto oResponse = null;
		
		
		try
		{
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oEm.merge(oDto);	
			oResponse = oDto;
		
		oTrans.commit();
		}
		catch(Exception e)
		{
			oTrans.rollback();
		}
		
		
		finally
		{
			oEm.close();
			
		}
		
		return oResponse;
		
	
	}
	
	
	
	public synchronized List<ProgramDto> findAll()
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		List<ProgramDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM ProgramDto e");
			oResponse =  query.getResultList();			 	
			
		}
		catch(Exception e)
		{
			
		}
		
		
		finally
		{
			oEm.close();
			
		}
		
		return oResponse;
				
		
	}
	
	
	public synchronized ProgramDto find(ProgramDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		ProgramDto oResponse = null;
		
		
		try
		{
			
			
			oResponse = oEm.find(ProgramDto.class, pDto.getRid());
			
		}
		catch(Exception e)
		{
			
		}
		
		
		finally
		{
			oEm.close();
		}
		
		return oResponse;
				
		
	}
	
	
	
	

}
