package org.wsbn.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.internal.SessionImpl;
import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.NclexAnnualDataDto;
import org.wsbn.dto.reports.NclexAnnualDataReport;
import org.wsbn.dto.reports.SchoolAnnualDataReport;
import org.wsbn.dto.reports.SchoolAnnualDataReport.eData;

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
	
	
	public synchronized List<NclexAnnualDataReport> findByPnProgramGroup()
	{

		// variables
		String sSQL = null;
		java.sql.Connection connection = null;
		java.sql.Statement stmt = null;
		
		
				
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
				

		List<NclexAnnualDataReport> oResponse = null;

		try {

			
			

			sSQL = "SELECT * " +
			  "FROM " +
			    "(" +
			       "SELECT A.SCHOOL_RID, A.PROGRAM_GROUP_RID, P.GROUP_NAME, A.[YEAR], A.NCLEX_PASS_PERCENT, "  +
			        "S.PASS_RATE, S.NAME, S.PRINT_INDEX " +
			        "FROM NCLEX_ANNUAL_DATA AS A, SCHOOLS AS S, PROGRAM_GROUPS_LK AS P " + 
			        "WHERE " +
			        "A.PROGRAM_GROUP_RID = P.RID " +
			        "AND A.SCHOOL_RID = S.RID " +
			        
			        
			    ") AS D " +
			"PIVOT " +
			  "(" +
			    "SUM( NCLEX_PASS_PERCENT ) " +
			    "FOR [YEAR] IN([2008],[2009],[2010],[2011],[2012],[2013],[2014],[2015] ) " +
			  ") AS [PIVOT] " +
			
			"WHERE PROGRAM_GROUP_RID = 1 " +
			"ORDER BY PRINT_INDEX, NAME ";		
			
			
			/*
			Query query = oEm.createNativeQuery(sSQL, ProgramAdmissionDto.class);
			oResponse = query.getResultList();
			*/
			
			
			//oEm.getTransaction().begin();
			//connection = oEm.unwrap(java.sql.Connection.class);
			connection = oEm.unwrap(SessionImpl.class).connection();
			stmt = connection.createStatement();
			
			ResultSet oRS = stmt.executeQuery(sSQL);
						
			while(oRS.next())
			{
				if( oResponse == null)   oResponse = new ArrayList<NclexAnnualDataReport>();
				
				NclexAnnualDataReport oDto = new NclexAnnualDataReport();
				oDto.setSchoolRid(oRS.getLong(1));
				oDto.setProgramRid(oRS.getLong(2));
				// manual
				oDto.setDegreeRid(1L);		
				
				oDto.setSchoolName(oRS.getString(4));

				oDto.setY2008(oRS.getBigDecimal(7));	
				oDto.setY2009(oRS.getBigDecimal(8));
				oDto.setY2010(oRS.getBigDecimal(9));
				oDto.setY2011(oRS.getBigDecimal(10));
				oDto.setY2012(oRS.getBigDecimal(11));
				oDto.setY2013(oRS.getBigDecimal(12));
				oDto.setY2014(oRS.getBigDecimal(13));
				oDto.setY2015(oRS.getBigDecimal(14));
				
				oResponse.add(oDto);
			}

			oRS.close();
			

		}
		catch (Exception e) {
			 
			 
		}

		finally 
		{
			try
			{
				if(connection!=null) connection.close();
			}
			catch(SQLException se)
			{
				
			}
			
			oEm.close();

		}

		return oResponse;

	}
	
	public synchronized List<NclexAnnualDataReport> findByAdnBsnProgramGroups()
	{

		// variables
		String sSQL = null;
		java.sql.Connection connection = null;
		java.sql.Statement stmt = null;
		
		
				
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
				

		List<NclexAnnualDataReport> oResponse = null;

		try {

			
			
			sSQL = "SELECT * " +
					  "FROM " +
					    "(" +
					       "SELECT A.SCHOOL_RID, A.PROGRAM_GROUP_RID, P.GROUP_NAME, A.[YEAR], A.NCLEX_PASS_PERCENT, "  +
					        "S.PASS_RATE, S.NAME, S.PRINT_INDEX " +
					        "FROM NCLEX_ANNUAL_DATA AS A, SCHOOLS AS S, PROGRAM_GROUPS_LK AS P " + 
					        "WHERE " +
					        "A.PROGRAM_GROUP_RID = P.RID " +
					        "AND A.SCHOOL_RID = S.RID " +
					        
					        
					    ") AS D " +
					"PIVOT " +
					  "(" +
					    "SUM( NCLEX_PASS_PERCENT ) " +
					    "FOR [YEAR] IN([2008],[2009],[2010],[2011],[2012],[2013],[2014],[2015] ) " +
					  ") AS [PIVOT] " +
					
					"WHERE PROGRAM_GROUP_RID = 6 " +
					"ORDER BY PRINT_INDEX, NAME ";		
					
					
			
			
			/*
			Query query = oEm.createNativeQuery(sSQL, ProgramAdmissionDto.class);
			oResponse = query.getResultList();
			*/
			
			
			//oEm.getTransaction().begin();
			//connection = oEm.unwrap(java.sql.Connection.class);
			connection = oEm.unwrap(SessionImpl.class).connection();
			stmt = connection.createStatement();
			
			ResultSet oRS = stmt.executeQuery(sSQL);
						
			while(oRS.next())
			{
				if( oResponse == null)   oResponse = new ArrayList<NclexAnnualDataReport>();
				
				NclexAnnualDataReport oDto = new NclexAnnualDataReport();
				oDto.setSchoolRid(oRS.getLong(1));
				oDto.setProgramRid(oRS.getLong(2));
				// manual
				oDto.setDegreeRid(1L);		
				
				oDto.setSchoolName(oRS.getString(4));
				
				oDto.setY2008(oRS.getBigDecimal(7));	
				oDto.setY2009(oRS.getBigDecimal(8));
				oDto.setY2010(oRS.getBigDecimal(9));
				oDto.setY2011(oRS.getBigDecimal(10));
				oDto.setY2012(oRS.getBigDecimal(11));
				oDto.setY2013(oRS.getBigDecimal(12));
				oDto.setY2014(oRS.getBigDecimal(13));
				oDto.setY2015(oRS.getBigDecimal(14));
				
				oResponse.add(oDto);
			}

			oRS.close();
			

		}
		catch (Exception e) {
			 
			 
		}

		finally 
		{
			try
			{
				if(connection!=null) connection.close();
			}
			catch(SQLException se)
			{
				
			}
			
			oEm.close();

		}

		return oResponse;

	}

}
