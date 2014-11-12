package org.wsbn.vo;

import java.io.Serializable;


public class SchoolDetailsVo implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	// STATE

	
	private String				rid;

	 
	private String				schoolRid;
	
		
	 
	private String schoolName;

		
	 
	private String				year;
	
	 
	private String	regionalAccreditation;
	
	 
	private String	mostRecentAccreditation;
	
	 
	private String	approvalType;
	
	 
	private String	nextVisit;
	
	 
	private String	mostRecentNlnacAccreditation;
	
	 
	private String	nlnacApprovalTpe;
	
	 
	private String	nlnacNextVisit;
	
	 
	private String	nlnacLastVisitRecommendations1;
	
	 
	private String	nlnacLastVisitRecommendations2;
	
	 
	private String	nlnacLastVisitRecommendations3;
	
	 
	private String	nlnacLastVisitRecommendations4;
	
	 
	private String	nlnacLastVisitRecommendations5;
	
	 
	private String	wsbnLastVisitRecommendations1;
	
	 
	private String	wsbnLastVisitRecommendations2;
	
	 
	private String	wsbnLastVisitRecommendations3;
	
	 
	private String	wsbnLastVisitRecommendations4;
	
	 
	private String	wsbnLastVisitRecommendations5;
	
	 
	private String	nclexPassRate;
	
	 
	private String	nursingProgramChanges;
	
	 
	private String	administrationQualifications;
	
	 
	private String	fqs;
	
	 
	private String	faculty;
		
	
	// CONSTRUCTOR(S)
	public SchoolDetailsVo() 
	{
		 
	}
	public SchoolDetailsVo(String pRid) 
	{
		this.rid = pRid;
	}
	
	
	// =============================== GETTERS/SETTERS ======================================
	/**
	 * @return the schoolRid
	 */
	public String getSchoolRid()
	{
		return schoolRid;
	}
	/**
	 * @param schoolRid the schoolRid to set
	 */
	public void setSchoolRid(String schoolRid)
	{
		this.schoolRid = schoolRid;
	}
	/**
	 * @return the schoolName
	 */
	public String getSchoolName()
	{
		return schoolName;
	}
	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}
	/**
	 * @return the year
	 */
	public String getYear()
	{
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year)
	{
		this.year = year;
	}
	/**
	 * @return the regionalAccreditation
	 */
	public String getRegionalAccreditation()
	{
		return regionalAccreditation;
	}
	/**
	 * @param regionalAccreditation the regionalAccreditation to set
	 */
	public void setRegionalAccreditation(String regionalAccreditation)
	{
		this.regionalAccreditation = regionalAccreditation;
	}
	/**
	 * @return the mostRecentAccreditation
	 */
	public String getMostRecentAccreditation()
	{
		return mostRecentAccreditation;
	}
	/**
	 * @param mostRecentAccreditation the mostRecentAccreditation to set
	 */
	public void setMostRecentAccreditation(String mostRecentAccreditation)
	{
		this.mostRecentAccreditation = mostRecentAccreditation;
	}
	/**
	 * @return the approvalType
	 */
	public String getApprovalType()
	{
		return approvalType;
	}
	/**
	 * @param approvalType the approvalType to set
	 */
	public void setApprovalType(String approvalType)
	{
		this.approvalType = approvalType;
	}
	/**
	 * @return the nextVisit
	 */
	public String getNextVisit()
	{
		return nextVisit;
	}
	/**
	 * @param nextVisit the nextVisit to set
	 */
	public void setNextVisit(String nextVisit)
	{
		this.nextVisit = nextVisit;
	}
	/**
	 * @return the mostRecentNlnacAccreditation
	 */
	public String getMostRecentNlnacAccreditation()
	{
		return mostRecentNlnacAccreditation;
	}
	/**
	 * @param mostRecentNlnacAccreditation the mostRecentNlnacAccreditation to set
	 */
	public void setMostRecentNlnacAccreditation(String mostRecentNlnacAccreditation)
	{
		this.mostRecentNlnacAccreditation = mostRecentNlnacAccreditation;
	}
	/**
	 * @return the nlnacApprovalTpe
	 */
	public String getNlnacApprovalTpe()
	{
		return nlnacApprovalTpe;
	}
	/**
	 * @param nlnacApprovalTpe the nlnacApprovalTpe to set
	 */
	public void setNlnacApprovalTpe(String nlnacApprovalTpe)
	{
		this.nlnacApprovalTpe = nlnacApprovalTpe;
	}
	/**
	 * @return the nlnacNextVisit
	 */
	public String getNlnacNextVisit()
	{
		return nlnacNextVisit;
	}
	/**
	 * @param nlnacNextVisit the nlnacNextVisit to set
	 */
	public void setNlnacNextVisit(String nlnacNextVisit)
	{
		this.nlnacNextVisit = nlnacNextVisit;
	}
	/**
	 * @return the nlnacLastVisitRecommendations1
	 */
	public String getNlnacLastVisitRecommendations1()
	{
		return nlnacLastVisitRecommendations1;
	}
	/**
	 * @param nlnacLastVisitRecommendations1 the nlnacLastVisitRecommendations1 to set
	 */
	public void setNlnacLastVisitRecommendations1(String nlnacLastVisitRecommendations1)
	{
		this.nlnacLastVisitRecommendations1 = nlnacLastVisitRecommendations1;
	}
	/**
	 * @return the nlnacLastVisitRecommendations2
	 */
	public String getNlnacLastVisitRecommendations2()
	{
		return nlnacLastVisitRecommendations2;
	}
	/**
	 * @param nlnacLastVisitRecommendations2 the nlnacLastVisitRecommendations2 to set
	 */
	public void setNlnacLastVisitRecommendations2(String nlnacLastVisitRecommendations2)
	{
		this.nlnacLastVisitRecommendations2 = nlnacLastVisitRecommendations2;
	}
	/**
	 * @return the nlnacLastVisitRecommendations3
	 */
	public String getNlnacLastVisitRecommendations3()
	{
		return nlnacLastVisitRecommendations3;
	}
	/**
	 * @param nlnacLastVisitRecommendations3 the nlnacLastVisitRecommendations3 to set
	 */
	public void setNlnacLastVisitRecommendations3(String nlnacLastVisitRecommendations3)
	{
		this.nlnacLastVisitRecommendations3 = nlnacLastVisitRecommendations3;
	}
	/**
	 * @return the nlnacLastVisitRecommendations4
	 */
	public String getNlnacLastVisitRecommendations4()
	{
		return nlnacLastVisitRecommendations4;
	}
	/**
	 * @param nlnacLastVisitRecommendations4 the nlnacLastVisitRecommendations4 to set
	 */
	public void setNlnacLastVisitRecommendations4(String nlnacLastVisitRecommendations4)
	{
		this.nlnacLastVisitRecommendations4 = nlnacLastVisitRecommendations4;
	}
	/**
	 * @return the nlnacLastVisitRecommendations5
	 */
	public String getNlnacLastVisitRecommendations5()
	{
		return nlnacLastVisitRecommendations5;
	}
	/**
	 * @param nlnacLastVisitRecommendations5 the nlnacLastVisitRecommendations5 to set
	 */
	public void setNlnacLastVisitRecommendations5(String nlnacLastVisitRecommendations5)
	{
		this.nlnacLastVisitRecommendations5 = nlnacLastVisitRecommendations5;
	}
	/**
	 * @return the wsbnLastVisitRecommendations1
	 */
	public String getWsbnLastVisitRecommendations1()
	{
		return wsbnLastVisitRecommendations1;
	}
	/**
	 * @param wsbnLastVisitRecommendations1 the wsbnLastVisitRecommendations1 to set
	 */
	public void setWsbnLastVisitRecommendations1(String wsbnLastVisitRecommendations1)
	{
		this.wsbnLastVisitRecommendations1 = wsbnLastVisitRecommendations1;
	}
	/**
	 * @return the wsbnLastVisitRecommendations2
	 */
	public String getWsbnLastVisitRecommendations2()
	{
		return wsbnLastVisitRecommendations2;
	}
	/**
	 * @param wsbnLastVisitRecommendations2 the wsbnLastVisitRecommendations2 to set
	 */
	public void setWsbnLastVisitRecommendations2(String wsbnLastVisitRecommendations2)
	{
		this.wsbnLastVisitRecommendations2 = wsbnLastVisitRecommendations2;
	}
	/**
	 * @return the wsbnLastVisitRecommendations3
	 */
	public String getWsbnLastVisitRecommendations3()
	{
		return wsbnLastVisitRecommendations3;
	}
	/**
	 * @param wsbnLastVisitRecommendations3 the wsbnLastVisitRecommendations3 to set
	 */
	public void setWsbnLastVisitRecommendations3(String wsbnLastVisitRecommendations3)
	{
		this.wsbnLastVisitRecommendations3 = wsbnLastVisitRecommendations3;
	}
	/**
	 * @return the wsbnLastVisitRecommendations4
	 */
	public String getWsbnLastVisitRecommendations4()
	{
		return wsbnLastVisitRecommendations4;
	}
	/**
	 * @param wsbnLastVisitRecommendations4 the wsbnLastVisitRecommendations4 to set
	 */
	public void setWsbnLastVisitRecommendations4(String wsbnLastVisitRecommendations4)
	{
		this.wsbnLastVisitRecommendations4 = wsbnLastVisitRecommendations4;
	}
	/**
	 * @return the wsbnLastVisitRecommendations5
	 */
	public String getWsbnLastVisitRecommendations5()
	{
		return wsbnLastVisitRecommendations5;
	}
	/**
	 * @param wsbnLastVisitRecommendations5 the wsbnLastVisitRecommendations5 to set
	 */
	public void setWsbnLastVisitRecommendations5(String wsbnLastVisitRecommendations5)
	{
		this.wsbnLastVisitRecommendations5 = wsbnLastVisitRecommendations5;
	}
	/**
	 * @return the nclexPassRate
	 */
	public String getNclexPassRate()
	{
		return nclexPassRate;
	}
	/**
	 * @param nclexPassRate the nclexPassRate to set
	 */
	public void setNclexPassRate(String nclexPassRate)
	{
		this.nclexPassRate = nclexPassRate;
	}
	/**
	 * @return the nursingProgramChanges
	 */
	public String getNursingProgramChanges()
	{
		return nursingProgramChanges;
	}
	/**
	 * @param nursingProgramChanges the nursingProgramChanges to set
	 */
	public void setNursingProgramChanges(String nursingProgramChanges)
	{
		this.nursingProgramChanges = nursingProgramChanges;
	}
	/**
	 * @return the administrationQualifications
	 */
	public String getAdministrationQualifications()
	{
		return administrationQualifications;
	}
	/**
	 * @param administrationQualifications the administrationQualifications to set
	 */
	public void setAdministrationQualifications(String administrationQualifications)
	{
		this.administrationQualifications = administrationQualifications;
	}
	/**
	 * @return the fqs
	 */
	public String getFqs()
	{
		return fqs;
	}
	/**
	 * @param fqs the fqs to set
	 */
	public void setFqs(String fqs)
	{
		this.fqs = fqs;
	}
	/**
	 * @return the faculty
	 */
	public String getFaculty()
	{
		return faculty;
	}
	/**
	 * @param faculty the faculty to set
	 */
	public void setFaculty(String faculty)
	{
		this.faculty = faculty;
	}
	/**
	 * @return the rid
	 */
	public String getRid()
	{
		return rid;
	}

	
	
	

}
