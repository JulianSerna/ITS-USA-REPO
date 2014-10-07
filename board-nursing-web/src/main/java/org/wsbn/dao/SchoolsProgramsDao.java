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
	public void saveEntity(SchoolDto pScholDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		
		try {
			
			oTrans = oEm.getTransaction();
			oTrans.begin();
			
			// delete all the school program dtos for the school in question ...
			this. deleteSchoolPrograms(pScholDto);
			
			// for each school program rid
			for(Long programRid : pScholDto.getProgramRids())
			{
				// add the SchoolProgramDto entity to the database ....
				SchoolProgramDto newDto = new SchoolProgramDto(pScholDto.getRid(), programRid);
				oEm.persist(newDto);
				
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
	
	public synchronized List<SchoolProgramDto> findSchoolProgramDtoList(SchoolDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<SchoolProgramDto> oResponse = null;

		try {

			Query query = oEm.createQuery("SELECT e FROM SchoolProgramDto e WHERE e.schoolRid = " + pDto.getRid() + "");
			oResponse = query.getResultList();

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}

		return oResponse;

	}
	
	public synchronized void deleteSchoolPrograms(SchoolDto pDto)
	{
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		

		try {

			Query query = oEm.createQuery("DELETE e FROM SchoolProgramDto e WHERE e.schoolRid = " + pDto.getRid() + "");
			query.getResultList();

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}

	

	}

	
	public synchronized SchoolProgramDto findEntity(SchoolProgramDto pDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		Object oResponse = null;

		try {
			//TODO:JULS:implement this ...
			//oResponse = oEm.find(pDto.getClass(), pDto.getRid());

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}

		return (SchoolProgramDto) oResponse;

	}

	public synchronized List<SchoolProgramDto> findAll()
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<SchoolProgramDto> oResponse = null;

		try {

			Query query = oEm.createQuery("SELECT e FROM SchoolProgramDto e");
			oResponse = query.getResultList();

		}
		catch (Exception e) {

		}

		finally {
			oEm.close();
			
		}
		
		return oResponse;
		

	}

}
