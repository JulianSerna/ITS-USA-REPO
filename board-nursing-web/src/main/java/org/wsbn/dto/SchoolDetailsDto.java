package org.wsbn.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.wsbn.vo.SchoolDetailsVo;

@Entity
@Table(name = "SCHOOL_DETAILS")
public class SchoolDetailsDto implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	// STATE

	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long				rid;

	@Column(name = "SCHOOL_RID")
	private Long				schoolRid;
	
		
	@Transient
	private String schoolName;

		
	@Column(name = "YEAR")
	private Integer				year;
	
	@Column(name = "REGIONAL_ACCREDITATION")
	private String	regionalAccreditation;
	
	@Column(name = "MOST_RECENT_ACCREDITATION")
	private String	mostRecentAccreditation;
	
	@Column(name = "APPROVAL_TYPE")
	private String	approvalType;
	
	@Column(name = "NEXT_VISIT")
	private String	nextVisit;
	
	@Column(name = "MOST_RECENT_NLNAC_ACCREDITATION")
	private String	mostRecentNlnacAccreditation;
	
	@Column(name = "NLNAC_APPROVAL_TYPE")
	private String	nlnacApprovalTpe;
	
	@Column(name = "NLNAC_NEXT_VISIT")
	private String	nlnacNextVisit;
	
	@Column(name = "NLNAC_LAST_VISIT_RECOMMENDATIONS_1")
	private String	nlnacLastVisitRecommendations1;
	
	@Column(name = "NLNAC_LAST_VISIT_RECOMMENDATIONS_2")
	private String	nlnacLastVisitRecommendations2;
	
	@Column(name = "NLNAC_LAST_VISIT_RECOMMENDATIONS_3")
	private String	nlnacLastVisitRecommendations3;
	
	@Column(name = "NLNAC_LAST_VISIT_RECOMMENDATIONS_4")
	private String	nlnacLastVisitRecommendations4;
	
	@Column(name = "NLNAC_LAST_VISIT_RECOMMENDATIONS_5")
	private String	nlnacLastVisitRecommendations5;
	
	@Column(name = "WSBN_LAST_VISIT_RECOMMENDATIONS_1")
	private String	wsbnLastVisitRecommendations1;
	
	@Column(name = "WSBN_LAST_VISIT_RECOMMENDATIONS_2")
	private String	wsbnLastVisitRecommendations2;
	
	@Column(name = "WSBN_LAST_VISIT_RECOMMENDATIONS_3")
	private String	wsbnLastVisitRecommendations3;
	
	@Column(name = "WSBN_LAST_VISIT_RECOMMENDATIONS_4")
	private String	wsbnLastVisitRecommendations4;
	
	@Column(name = "WSBN_LAST_VISIT_RECOMMENDATIONS_5")
	private String	wsbnLastVisitRecommendations5;
	
	@Column(name = "NCLEX_PASS_RATE")
	private String	nclexPassRate;
	
	@Column(name = "NURSING_PROGRAM_CHANGES")
	private String	nursingProgramChanges;
	
	@Column(name = "ADMINISTRATION_QUALIFICATIONS")
	private String	administrationQualifications;
	
	@Column(name = "FQS")
	private String	fqs;
	
	@Column(name = "FACULTY")
	private String	faculty;
	
	
	
	
	// CONSTRUCTOR(S)
	public SchoolDetailsDto() 
	{
		 
	}
	public SchoolDetailsDto(Long pRid) 
	{
		this.rid = pRid;
	}
	
	public SchoolDetailsDto(SchoolDetailsVo pVo) 
	{
		
		// method validation
		if (pVo  == null) return;
						
		this.rid =      (  pVo.getRid() == null ? null :  Long.parseLong(pVo.getRid())    );
		this.schoolRid = Long.parseLong(pVo.getSchoolRid());
		this.year = Integer.parseInt(pVo.getYear());
		this.setRegionalAccreditation(pVo.getRegionalAccreditation());
		this.setMostRecentAccreditation(pVo.getMostRecentAccreditation());
		this.setApprovalType(pVo.getApprovalType());
		this.setNextVisit(pVo.getNextVisit());
		this.setMostRecentNlnacAccreditation(pVo.getMostRecentNlnacAccreditation());
		this.setNlnacApprovalTpe(pVo.getNlnacApprovalTpe());
		this.setNlnacNextVisit(pVo.getNlnacNextVisit());
		this.setNlnacLastVisitRecommendations1(pVo.getNlnacLastVisitRecommendations1());
		this.setNlnacLastVisitRecommendations2(pVo.getNlnacLastVisitRecommendations2());
		this.setNlnacLastVisitRecommendations3(pVo.getNlnacLastVisitRecommendations3());
		this.setNlnacLastVisitRecommendations4(pVo.getNlnacLastVisitRecommendations4());
		this.setNlnacLastVisitRecommendations5(pVo.getNlnacLastVisitRecommendations5());
		this.setWsbnLastVisitRecommendations1(pVo.getWsbnLastVisitRecommendations1());
		this.setWsbnLastVisitRecommendations2(pVo.getWsbnLastVisitRecommendations2());
		this.setWsbnLastVisitRecommendations3(pVo.getWsbnLastVisitRecommendations3());
		this.setWsbnLastVisitRecommendations4(pVo.getWsbnLastVisitRecommendations4());
		this.setWsbnLastVisitRecommendations5(pVo.getWsbnLastVisitRecommendations5());
		this.setNclexPassRate(pVo.getNclexPassRate());
		this.setNursingProgramChanges(pVo.getNursingProgramChanges());
		this.setAdministrationQualifications(pVo.getAdministrationQualifications());
		this.setFqs(pVo.getFqs());
		this.setFaculty(pVo.getFaculty());
		
	 
		
	}
	
	
	
	// =============================== GETTERS/SETTERS ======================================
	/**
	 * @return the schoolRid
	 */
	public Long getSchoolRid()
	{
		return schoolRid;
	}
	/**
	 * @param schoolRid the schoolRid to set
	 */
	public void setSchoolRid(Long schoolRid)
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
	public Integer getYear()
	{
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year)
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
	public Long getRid()
	{
		return rid;
	}

	// ======================================== METHODS ===================================================
	
	public SchoolDetailsVo getVo()
	{
		
		// method validation
		if (this.rid  == null) return null;
				
		SchoolDetailsVo oVo = new SchoolDetailsVo( this.rid.toString()   );
		
		
		oVo.setSchoolRid(this.schoolRid.toString());
		oVo.setYear(this.year.toString());
		
		oVo.setRegionalAccreditation(regionalAccreditation);
		oVo.setMostRecentAccreditation(mostRecentAccreditation);
		oVo.setApprovalType(approvalType);
		oVo.setNextVisit(nextVisit);
		oVo.setMostRecentNlnacAccreditation(mostRecentNlnacAccreditation);
		oVo.setNlnacApprovalTpe(nlnacApprovalTpe);
		oVo.setNlnacNextVisit(nlnacNextVisit);
		oVo.setNlnacLastVisitRecommendations1(nlnacLastVisitRecommendations1);
		oVo.setNlnacLastVisitRecommendations2(nlnacLastVisitRecommendations2);
		oVo.setNlnacLastVisitRecommendations3(nlnacLastVisitRecommendations3);
		oVo.setNlnacLastVisitRecommendations4(nlnacLastVisitRecommendations4);
		oVo.setNlnacLastVisitRecommendations5(nlnacLastVisitRecommendations5);
		oVo.setWsbnLastVisitRecommendations1(wsbnLastVisitRecommendations1);
		oVo.setWsbnLastVisitRecommendations2(wsbnLastVisitRecommendations2);
		oVo.setWsbnLastVisitRecommendations3(wsbnLastVisitRecommendations3);
		oVo.setWsbnLastVisitRecommendations4(wsbnLastVisitRecommendations4);
		oVo.setWsbnLastVisitRecommendations5(wsbnLastVisitRecommendations5);
		oVo.setNclexPassRate(nclexPassRate);
		oVo.setNursingProgramChanges(nursingProgramChanges);
		oVo.setAdministrationQualifications(administrationQualifications);
		oVo.setFqs(fqs);
		oVo.setFaculty(faculty);
		
		//oVo.setY2008( (this.Y2008 != null) ? this.Y2008.toString() : "0.00");
				
		return oVo;
		
		
		
	}
	

}
