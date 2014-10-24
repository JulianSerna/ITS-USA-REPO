package org.wsbn.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.DegreeDto;

public class DegreesDao implements Serializable

{	
	 
	
	// COLLABORATORS
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	// CONSTRUCTOR(S)
	public DegreesDao()
	{
		
		
	}
	
	
	@SuppressWarnings("finally")
	public synchronized DegreeDto addEntity(DegreeDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		DegreeDto oResponse = null;
		
		
		try
		{
			
			// aut set next print index
			Query q = oEm.createQuery( "SELECT COUNT(e.rid) FROM DegreeDto e" );
			int count = ( (Integer) q.getSingleResult() ).intValue();
			oDto.setPrintIndex(count);
			
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
	
	
	public synchronized DegreeDto disableEntity(DegreeDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		DegreeDto oResponse = null;
		DegreeDto oDto = new DegreeDto();
				
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
	
	
	public synchronized DegreeDto enableEntity(DegreeDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		DegreeDto oResponse = null;
		DegreeDto oDto = new DegreeDto();
				
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
	
	public synchronized DegreeDto saveEntity(DegreeDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		DegreeDto oResponse = null;
		
		
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
	
	
	
	public synchronized List<DegreeDto> findAll()
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		List<DegreeDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM DegreeDto e WHERE e.disabled = :pDisabled ORDER BY e.printIndex, e.name ");
			query.setParameter("pDisabled", false);
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
	
	
	public synchronized DegreeDto find(DegreeDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		DegreeDto oResponse = null;
		
		
		try
		{
			
			
			oResponse = oEm.find(DegreeDto.class, pDto.getRid());
			
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
