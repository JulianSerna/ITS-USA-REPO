package org.wsbn.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.YearDto;

public class YearsDao implements Serializable

{	
	 
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	// COLLABORATORS
	
	
	// CONSTRUCTOR(S)
	public YearsDao()
	{
		
		
	}
	
	
	@SuppressWarnings("finally")
	public synchronized YearDto addEntity(YearDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		YearDto oResponse = null;
		
		
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
	
	
	
	
	
	 
	
	
	
	
	public synchronized List<YearDto> findAll()
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		List<YearDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM YearDto e ORDER BY e.year ASC");
			oResponse = query.getResultList();			 	
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		finally
		{
			oEm.close();
			
		}
		
		return oResponse;
				
		
	}
	
	public synchronized int deleteEntity(YearDto oDto)
	{
		
		// get entity manager
		int iCount = 0;
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		

		try 
		{
			
			oTrans = oEm.getTransaction();
			oTrans.begin();
			
			Query query = oEm.createQuery("DELETE FROM YearDto e WHERE e.year = :year");
			query.setParameter("year", oDto.getYear());
			iCount = query.executeUpdate();
			oTrans.commit();
			
		}
		catch (Exception e) 
		{
			oTrans.rollback();
		}

		finally {
			oEm.close();
			
		}

		return iCount;
		
	}
	
	
	
	
	
	
	
	

}
