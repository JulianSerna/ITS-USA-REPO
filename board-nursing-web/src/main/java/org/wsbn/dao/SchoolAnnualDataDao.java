package org.wsbn.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.internal.SessionImpl;
import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.SchoolAnnualDataDto;
import org.wsbn.dto.reports.SchoolAnnualDataReport;
import org.wsbn.dto.reports.SchoolAnnualDataReport.eData;

public class SchoolAnnualDataDao implements Serializable

{

	private static final long	serialVersionUID	= 1L;

	// COLLABORATORS

	public SchoolAnnualDataDao() {

	}

	@SuppressWarnings("finally")
	public synchronized SchoolAnnualDataDto saveEntity(SchoolAnnualDataDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		SchoolAnnualDataDto oResponse = null;

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

	public synchronized int deleteEntity(SchoolAnnualDataDto oDto)
	{

		// get entity manager
		int iCount = 0;
		EntityManager oEm = PersistenceManager.createEntityManager();
		EntityTransaction oTrans = null;

		try {

			oTrans = oEm.getTransaction();
			oTrans.begin();

			Query query = oEm.createQuery("DELETE FROM SchoolAnnualDataDto e WHERE e.rid = :scholRid");
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

	public synchronized SchoolAnnualDataDto addEntity(SchoolAnnualDataDto oDto)
	{

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		EntityTransaction oTrans = null;
		SchoolAnnualDataDto oResponse = null;

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

		try {

			Query query = oEm.createQuery("SELECT e FROM SchoolAnnualDataDto e ORDER BY e.year DESC");
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

	public synchronized List<SchoolAnnualDataDto> findBySchoolRid(Long schoolRid)
	{

		// variables
		String sSQL = null;

		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();

		List<SchoolAnnualDataDto> oResponse = null;

		try {

			// TODO:JULS:create a joint and ensure list is also sorted by degree
			// print index
			sSQL = "SELECT A.*, D.PRINT_INDEX " + 
			"FROM SCHOOL_ANNUAL_DATA AS A, DEGREES_LK AS D " +
			"WHERE A.DEGREE_RID = D.RID " + "AND A.SCHOOL_RID = " + schoolRid + " " +
			"ORDER BY A.PROGRAM_RID, D.PRINT_INDEX, A.YEAR DESC ";

			//Query query = oEm.createQuery(sSQL);
			// query.setParameter("schoolRid", schoolRid);
			Query query = oEm.createNativeQuery(sSQL, SchoolAnnualDataDto.class);
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
	
	
	public synchronized List<SchoolAnnualDataReport> findByPnProgramGroup(eData pData)
	{

		// variables
		String sSQL = null;
		String sColName = null;
		java.sql.Connection connection = null;
		java.sql.Statement stmt = null;
		
		
		// set variables
		if(pData == eData.ADMISSIONS)
		{
			sColName = eData.ADMISSIONS.toString();
			
		}
		else if(pData == eData.GRADUATIONS)
		{
			sColName = eData.GRADUATIONS.toString();
		}
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
				

		List<SchoolAnnualDataReport> oResponse = null;

		try {

			// TODO:JULS:create a joint and ensure list is also sorted by degree
			// print index
			/*
			sSQL = "SELECT A.*, D.PRINT_INDEX , P.GROUP_RID, S.NAME " + 
			"FROM SCHOOL_ANNUAL_DATA AS A, DEGREES_LK AS D, PROGRAMS P, SCHOOLS AS S " +
			"WHERE A.DEGREE_RID = D.RID " + 
			"AND A.PROGRAM_RID = P.RID " +
			"AND A.SCHOOL_RID = S.RID " +
			"AND P.GROUP_RID = 1 "  +
			"ORDER BY S.NAME, A.PROGRAM_RID, D.PRINT_INDEX, A.YEAR DESC ";
			*/

			//Query query = oEm.createQuery(sSQL);
			// query.setParameter("schoolRid", schoolRid);
			
			sSQL = "SELECT * " +
			  "FROM " +
			    "(" +
			       "SELECT A.SCHOOL_RID,A.PROGRAM_RID,A.[YEAR],A." + sColName + ", " +
			        "P.GROUP_RID, S.NAME, S.PASS_RATE " +
			        "FROM SCHOOL_ANNUAL_DATA AS A, PROGRAMS P, SCHOOLS AS S " + 
			        "WHERE " +
			        "A.PROGRAM_RID = P.RID " +
			        "AND A.SCHOOL_RID = S.RID " +
			        
			        
			    ") AS D " +
			"PIVOT " +
			  "(" +
			    "SUM("   +   sColName +  ") " +
			    "FOR [YEAR] IN([2008],[2009],[2010],[2011],[2012],[2013],[2014],[2015] ) " +
			  ") AS [PIVOT] " +
			
			 "WHERE GROUP_RID = 1 " +
			 "AND PASS_RATE = 0 " +
			 
			"ORDER BY NAME, PROGRAM_RID ";		
			
			
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
				if( oResponse == null)   oResponse = new ArrayList<SchoolAnnualDataReport>();
				
				SchoolAnnualDataReport oDto = new SchoolAnnualDataReport();
				oDto.setSchoolRid(oRS.getLong(1));
				oDto.setProgramRid(oRS.getLong(2));
				// manual
				oDto.setDegreeRid(1L);		
				
				oDto.setSchoolName(oRS.getString(4));
				
				oDto.setY2008(oRS.getInt(6));	
				oDto.setY2009(oRS.getInt(7));
				oDto.setY2010(oRS.getInt(8));
				oDto.setY2011(oRS.getInt(9));
				oDto.setY2012(oRS.getInt(10));
				oDto.setY2013(oRS.getInt(11));
				oDto.setY2014(oRS.getInt(12));
				oDto.setY2015(oRS.getInt(13));
				
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
	
	public synchronized List<SchoolAnnualDataReport> findByAdnBsnProgramGroups(eData pData)
	{

		// variables
		String sSQL = null;
		String sColName = null;
		java.sql.Connection connection = null;
		java.sql.Statement stmt = null;
		
		// set variables
		if(pData == eData.ADMISSIONS)
		{
			sColName = eData.ADMISSIONS.toString();
			
		}
		else if(pData == eData.GRADUATIONS)
		{
			sColName = eData.GRADUATIONS.toString();
		}
		
		
		
		// get entity manager
		EntityManager oEm = PersistenceManager.createEntityManager();
				

		List<SchoolAnnualDataReport> oResponse = null;

		try {

			// TODO:JULS:create a joint and ensure list is also sorted by degree
			// print index
			/*
			sSQL = "SELECT A.*, D.PRINT_INDEX , P.GROUP_RID, S.NAME " + 
			"FROM SCHOOL_ANNUAL_DATA AS A, DEGREES_LK AS D, PROGRAMS P, SCHOOLS AS S " +
			"WHERE A.DEGREE_RID = D.RID " + 
			"AND A.PROGRAM_RID = P.RID " +
			"AND A.SCHOOL_RID = S.RID " +
			"AND P.GROUP_RID = 1 "  +
			"ORDER BY S.NAME, A.PROGRAM_RID, D.PRINT_INDEX, A.YEAR DESC ";
			*/

			//Query query = oEm.createQuery(sSQL);
			// query.setParameter("schoolRid", schoolRid);
			
			sSQL = "SELECT * " +
			  "FROM " +
			    "(" +
			    "SELECT A.SCHOOL_RID,A.PROGRAM_RID,A.[YEAR],A." + sColName + ", " +
			        "P.GROUP_RID, S.NAME, S.PASS_RATE " +
			        "FROM SCHOOL_ANNUAL_DATA AS A, PROGRAMS P, SCHOOLS AS S " + 
			        "WHERE " +
			        "A.PROGRAM_RID = P.RID " +
			        "AND A.SCHOOL_RID = S.RID " +
			        
			        
			    ") AS D " +
			"PIVOT " +
			  "(" +
			  "SUM("   +   sColName +  ") " +
			    "FOR [YEAR] IN([2008],[2009],[2010],[2011],[2012],[2013],[2014],[2015] ) " +
			  ") AS [PIVOT] " +
			
			 "WHERE GROUP_RID IN(2, 3 ) " +
			 "AND PASS_RATE = 0 " +
			 
			"ORDER BY PROGRAM_RID, NAME";		
			
			
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
				if( oResponse == null)   oResponse = new ArrayList<SchoolAnnualDataReport>();
				
				SchoolAnnualDataReport oDto = new SchoolAnnualDataReport();
				oDto.setSchoolRid(oRS.getLong(1));
				oDto.setProgramRid(oRS.getLong(2));
				// manual
				oDto.setDegreeRid(1L);
				oDto.setSchoolName(oRS.getString(4));
				
				oDto.setY2008(oRS.getInt(6));	
				oDto.setY2009(oRS.getInt(7));
				oDto.setY2010(oRS.getInt(8));
				oDto.setY2011(oRS.getInt(9));
				oDto.setY2012(oRS.getInt(10));
				oDto.setY2013(oRS.getInt(11));
				oDto.setY2014(oRS.getInt(12));
				oDto.setY2015(oRS.getInt(13));
				
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
	
	public synchronized List<SchoolAnnualDataReport> findByGraduatePrograms(eData pData)
	{

		// variables
				String sSQL = null;
				String sColName = null;
				java.sql.Connection connection = null;
				java.sql.Statement stmt = null;
				
				// set variables
				if(pData == eData.ADMISSIONS)
				{
					sColName = eData.ADMISSIONS.toString();
					
				}
				else if(pData == eData.GRADUATIONS)
				{
					sColName = eData.GRADUATIONS.toString();
				}
				
				
				
				// get entity manager
				EntityManager oEm = PersistenceManager.createEntityManager();
						

				List<SchoolAnnualDataReport> oResponse = null;

				try {

					// TODO:JULS:create a joint and ensure list is also sorted by degree
					// print index
					/*
					sSQL = "SELECT A.*, D.PRINT_INDEX , P.GROUP_RID, S.NAME " + 
					"FROM SCHOOL_ANNUAL_DATA AS A, DEGREES_LK AS D, PROGRAMS P, SCHOOLS AS S " +
					"WHERE A.DEGREE_RID = D.RID " + 
					"AND A.PROGRAM_RID = P.RID " +
					"AND A.SCHOOL_RID = S.RID " +
					"AND P.GROUP_RID = 1 "  +
					"ORDER BY S.NAME, A.PROGRAM_RID, D.PRINT_INDEX, A.YEAR DESC ";
					*/

					//Query query = oEm.createQuery(sSQL);
					// query.setParameter("schoolRid", schoolRid);
					
					sSQL = "SELECT * " +
					  "FROM " +
					    "(" +
					    "SELECT A.SCHOOL_RID,A.DEGREE_RID,A.[YEAR],A." + sColName + ", " +
					        "P.GROUP_RID, S.NAME, S.PASS_RATE " +
					        "FROM SCHOOL_ANNUAL_DATA AS A, PROGRAMS P, SCHOOLS AS S " + 
					        "WHERE " +
					        "A.PROGRAM_RID = P.RID " +
					        "AND A.SCHOOL_RID = S.RID " +
					        
					        
					    ") AS D " +
					"PIVOT " +
					  "(" +
					  "SUM("   +   sColName +  ") " +
					    "FOR [YEAR] IN([2008],[2009],[2010],[2011],[2012],[2013],[2014],[2015] ) " +
					  ") AS [PIVOT] " +
					
					 "WHERE DEGREE_RID > 1 " +
					 "AND PASS_RATE = 0 " +
					 
					"ORDER BY NAME";		
					
					
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
						if( oResponse == null)   oResponse = new ArrayList<SchoolAnnualDataReport>();
						
						SchoolAnnualDataReport oDto = new SchoolAnnualDataReport();
						oDto.setSchoolRid(oRS.getLong(1));
						oDto.setProgramRid(0L);
						// manual
						oDto.setDegreeRid(oRS.getLong(2));
						oDto.setSchoolName(oRS.getString(4));
						
						oDto.setY2008(oRS.getInt(6));	
						oDto.setY2009(oRS.getInt(7));
						oDto.setY2010(oRS.getInt(8));
						oDto.setY2011(oRS.getInt(9));
						oDto.setY2012(oRS.getInt(10));
						oDto.setY2013(oRS.getInt(11));
						oDto.setY2014(oRS.getInt(12));
						oDto.setY2015(oRS.getInt(13));
						
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
