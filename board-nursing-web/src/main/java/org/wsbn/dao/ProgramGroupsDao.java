package org.wsbn.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.ProgramGroupDto;

public class ProgramGroupsDao implements Serializable

{	
	 
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	// COLLABORATORS
	
	
	// CONSTRUCTOR(S)
	public ProgramGroupsDao()
	{
		
		
	}
	
	
	@SuppressWarnings("finally")
	public synchronized ProgramGroupDto addEntity(ProgramGroupDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		ProgramGroupDto oResponse = null;
		
		
		try
		{
			
			// aut set next print index
			Query q = oEm.createQuery( "SELECT COUNT(e.rid) FROM ProgramGroupDto e" );
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
	
	
	public synchronized ProgramGroupDto disableEntity(ProgramGroupDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		ProgramGroupDto oResponse = null;
		ProgramGroupDto oDto = new ProgramGroupDto();
				
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
	
	
	public synchronized ProgramGroupDto enableEntity(ProgramGroupDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		ProgramGroupDto oResponse = null;
		ProgramGroupDto oDto = new ProgramGroupDto();
				
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
	
	public synchronized ProgramGroupDto saveEntity(ProgramGroupDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		ProgramGroupDto oResponse = null;
		
		
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
	
	
	
	public synchronized List<ProgramGroupDto> findAll()
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		List<ProgramGroupDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM ProgramGroupDto e ORDER BY e.printIndex");
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
	
	
	public synchronized ProgramGroupDto find(ProgramGroupDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		ProgramGroupDto oResponse = null;
		
		
		try
		{
			
			
			oResponse = oEm.find(ProgramGroupDto.class, pDto.getRid());
			
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
