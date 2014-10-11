package org.wsbn.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.SchoolProgramDto;

public class SchoolsProgramsDao implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4347759637910273515L;

	// COLLABORATORS
	
	// STATE
	
	// lists
	
	

	// CONSTRUCTOR(S)
	public SchoolsProgramsDao() {

	}

	
	public SchoolProgramDto saveEntity(SchoolProgramDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		SchoolProgramDto oResponse = null;

		try {
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oEm.merge(oDto);
			oResponse = oDto;

			oTrans.commit();
		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {
			oEm.close();
			
		}

		return oResponse;

	}
	public void updateSchoolRids(SchoolDto pSchoolDto)
	{

		// validate procedure
		if(pSchoolDto.getRid() == null) return;
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		
		try {
			
			oTrans = oEm.getTransaction();
			oTrans.begin();
			
			// delete all the school program dtos for the school in question ...
			this.deleteSchoolPrograms(pSchoolDto);
			
			// if the school dto has school-program dtos ...
			if(pSchoolDto.getProgramRids() != null)
			{
				// for each school-program dto ...
				for(String programRid : pSchoolDto.getProgramRids())
				{
					// add the SchoolProgramDto entity to the database ....
					SchoolProgramDto newDto = new SchoolProgramDto(pSchoolDto.getRid(), Long.parseLong(programRid));
					oEm.persist(newDto);
					
				}
			}
									
			oTrans.commit();
		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {
			oEm.close();
			
		}

		

	}

	public synchronized void deleteEntity(SchoolProgramDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;

		try {
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oEm.remove(oDto);

			oTrans.commit();
		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {
			oEm.close();
		}

	}

	
	public synchronized SchoolProgramDto addEntity(SchoolProgramDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		SchoolProgramDto oResponse = null;

		try {
			oTrans = oEm.getTransaction();
			oTrans.begin();
			oEm.persist(oDto);
			oResponse = oDto;

			oTrans.commit();
		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {
			oEm.close();
			
		}

		return oResponse;

	}


	
	

	/**
	 * This method returns a SchoolProgramDto List of all records for a given
	 * SchoolDto
	 */
	
	@SuppressWarnings("unchecked")
	public synchronized List<SchoolProgramDto> findSchoolProgramDtoList(SchoolDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<SchoolProgramDto> oResponse = null;

		try {

			Query query = oEm.createQuery("SELECT e FROM SchoolProgramDto e WHERE e.schoolRid = :schoolRid");
			query.setParameter("schoolRid", pDto.getRid());
			oResponse = query.getResultList();

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}

		return oResponse;

	}
	
	public synchronized int deleteSchoolPrograms(SchoolDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
		
		EntityTransaction oTrans = null;
		
		int iCount = 0;

		

		try 
		{
			
			oTrans = oEm.getTransaction();
			oTrans.begin();
			
			Query query = oEm.createQuery("DELETE FROM SchoolProgramDto e WHERE e.schoolRid = :scholRid");
			query.setParameter("scholRid", pDto.getRid());
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

	
	public synchronized SchoolProgramDto findEntity(SchoolProgramDto pDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		Object oResponse = null;

		try {
			String sSql = "SELECT e FROM SchoolProgramDto e WHERE e.schoolRid = :schoolRid AND e.programRid = :programRid";
			Query query = oEm.createQuery(sSql);
			query.setParameter("schoolRid", pDto.getSchoolRid());
			query.setParameter("programRid", pDto.getProgramRid());
			oResponse = query.getResultList();

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}

		return (SchoolProgramDto) oResponse;

	}

	@SuppressWarnings("unchecked")
	public synchronized List<SchoolProgramDto> findAll()
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<SchoolProgramDto> oResponse = null;

		try {

			Query query = oEm.createQuery("SELECT e FROM SchoolProgramDto e");
			oResponse =  query.getResultList();

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}
		
		return oResponse;
		

	}

}
