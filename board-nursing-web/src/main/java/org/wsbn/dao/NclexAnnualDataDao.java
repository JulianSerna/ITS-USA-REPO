package org.wsbn.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.NclexAnnualDataDto;

public class NclexAnnualDataDao implements Serializable

{

	private static final long	serialVersionUID	= 1L;

	// COLLABORATORS

	public NclexAnnualDataDao() {

	}

	@SuppressWarnings("finally")
	public synchronized NclexAnnualDataDto saveEntity(NclexAnnualDataDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		NclexAnnualDataDto oResponse = null;

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

	public synchronized int deleteEntity(NclexAnnualDataDto oDto)
	{

		// get entity manager
		int iCount = 0;
		EntityManager oEm = PersistenceManager.createEntityManager();
		EntityTransaction oTrans = null;

		try {

			oTrans = oEm.getTransaction();
			oTrans.begin();

			Query query = oEm.createQuery("DELETE FROM NclexAnnualDataDto e WHERE e.rid = :scholRid");
			query.setParameter("scholRid", oDto.getRid());
			iCount = query.executeUpdate();
			oTrans.commit();

		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {
			oEm.close();

		}

		return iCount;

	}

	public synchronized NclexAnnualDataDto addEntity(NclexAnnualDataDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		NclexAnnualDataDto oResponse = null;

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

	public synchronized NclexAnnualDataDto find(NclexAnnualDataDto pDto)
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

		return (NclexAnnualDataDto) oResponse;

	}

	public synchronized List<NclexAnnualDataDto> findAll()
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<NclexAnnualDataDto> oResponse = null;

		try {

			Query query = oEm.createQuery("SELECT e FROM NclexAnnualDataDto e ORDER BY e.year DESC");
			oResponse = query.getResultList();

		}
		catch (Exception e) {
			System.out.println(e);
		}

		finally {
			oEm.close();

		}

		return oResponse;

	}

	
	public synchronized List<NclexAnnualDataDto> findBySchoolRid(Long schoolRid)
	{

		// variables
		String sSQL = null;

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<NclexAnnualDataDto> oResponse = null;

		try {

			// TODO:JULS:create a joint and ensure list is also sorted by degree
			// print index
			sSQL = "SELECT A.*, D.PRINT_INDEX " + 
			"FROM NCLEX_ANNUAL_DATA AS A, PROGRAM_GROUPS_LK AS D " +
			"WHERE A.PROGRAM_GROUP_RID = D.RID " + "AND A.SCHOOL_RID = " + schoolRid + " " +
			"ORDER BY D.PRINT_INDEX, A.YEAR DESC ";

			//Query query = oEm.createQuery(sSQL);
			// query.setParameter("schoolRid", schoolRid);
			Query query = oEm.createNativeQuery(sSQL, NclexAnnualDataDto.class);
			oResponse = query.getResultList();

			

		}
		catch (Exception e) {
			System.out.println(e);
		}

		finally {
			oEm.close();

		}

		return oResponse;

	}

}
