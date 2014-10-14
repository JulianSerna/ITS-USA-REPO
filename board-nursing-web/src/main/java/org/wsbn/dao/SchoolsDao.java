package org.wsbn.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.SchoolDto;


public class SchoolsDao implements Serializable

{	
	
	private static final long serialVersionUID = -1963193643818438220L;
	
	// COLLABORATORS
	 
	
	// CONSTRUCTOR(S)
	public SchoolsDao()
	{
		
		
	}
	
	@SuppressWarnings("finally")
	public synchronized SchoolDto saveEntity(SchoolDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		SchoolDto oResponse = null;
		
		
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
	
	public synchronized int deleteEntity(SchoolDto oDto)
	{
		
		// get entity manager
		int iCount = 0;
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		

		try 
		{
			
			oTrans = oEm.getTransaction();
			oTrans.begin();
			
			Query query = oEm.createQuery("DELETE FROM SchoolDto e WHERE e.rid = :scholRid");
			query.setParameter("scholRid", oDto.getRid());
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
	
	
	
	
	public synchronized SchoolDto addEntity(SchoolDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		SchoolDto oResponse = null;
		
		
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
		
	
	public synchronized SchoolDto find(SchoolDto pDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		Object oResponse = null;

		try {

			oResponse = oEm.find(pDto.getClass(), pDto.getRid());

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}

		return (SchoolDto) oResponse;

	}
	

	public synchronized List<SchoolDto> findAll()
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		List<SchoolDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM SchoolDto e ORDER BY e.name ");
			oResponse =  query.getResultList();			 	
			
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
	
	
	
	
	
	

}
