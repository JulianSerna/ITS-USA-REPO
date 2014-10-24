package org.wsbn.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.SchoolAnnualDataDto;


public class SchoolAnnualDataDao implements Serializable

{	
	
	private static final long	serialVersionUID	= 1L;
	
	// COLLABORATORS
	 	
	


	public SchoolAnnualDataDao()
	{
		
		
	}
	
	@SuppressWarnings("finally")
	public synchronized SchoolAnnualDataDto saveEntity(SchoolAnnualDataDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		SchoolAnnualDataDto oResponse = null;
		
		
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
	
	public synchronized int deleteEntity(SchoolAnnualDataDto oDto)
	{
		
		// get entity manager
		int iCount = 0;
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		EntityTransaction oTrans = null;
		

		try 
		{
			
			oTrans = oEm.getTransaction();
			oTrans.begin();
			
			Query query = oEm.createQuery("DELETE FROM SchoolAnnualDataDto e WHERE e.rid = :scholRid");
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
	
	
	
	
	public synchronized SchoolAnnualDataDto addEntity(SchoolAnnualDataDto oDto)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		EntityTransaction oTrans = null;
		SchoolAnnualDataDto oResponse = null;
		
		
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
		
	
	public synchronized SchoolAnnualDataDto find(SchoolAnnualDataDto pDto)
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

		return (SchoolAnnualDataDto) oResponse;

	}
	

	public synchronized List<SchoolAnnualDataDto> findAll()
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		List<SchoolAnnualDataDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM SchoolAnnualDataDto e ORDER BY e.year DESC");
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
	
	public synchronized List<SchoolAnnualDataDto> findBySchoolRid(Long schoolRid)
	{
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager(); 
		
		List<SchoolAnnualDataDto> oResponse = null;
				
		try
		{
			
			Query query = oEm.createQuery("SELECT e FROM SchoolAnnualDataDto e WHERE e.schoolRid = :schoolRid ORDER BY e.programRid, e.year DESC");
			query.setParameter("schoolRid", schoolRid);
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
