package org.wsbn.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.wsbn.vo.SchoolAnnualDataVo;

@Entity
@Table(name = "SCHOOL_ANNUAL_DATA")
public class SchoolAnnualDataDto implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	// STATE

	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long				rid;

	@Column(name = "SCHOOL_RID")
	private Long				schoolRid;

	@Column(name = "PROGRAM_RID")
	private Long				programRid;

	@Column(name = "DEGREE_RID")
	private Long				degreeRid;
	
	@Column(name = "YEAR")
	private Integer				year;

	@Column(name = "ADMISSIONS")
	private Integer				admissions;

	@Column(name = "GRADUATIONS")
	private Integer				graduations;

	@Column(name = "ATTRITION_ACADEMIC")
	private Integer				attritionA;

	@Column(name = "ATTRITION_PERSONAL")
	private Integer				attritionP;
	
	@Column(name = "NCLEX_PASS_PERCENT")
	private Double nclexPassPercent;

	
	
	// CONSTRUCTOR(S)
	public SchoolAnnualDataDto() 
	{
		this.attritionA = 0;
		this.attritionP = 0;
	}
	public SchoolAnnualDataDto(Long pRid) {
		this.rid = pRid;
		this.attritionA = 0;
		this.attritionP = 0;
		this.nclexPassPercent = (double) 0;
	}

	/**
	 * @return the schoolRid
	 */
	public Long getSchoolRid()
	{
		return schoolRid;
	}

	/**
	 * @param schoolRid
	 *            the schoolRid to set
	 */
	public void setSchoolRid(Long schoolRid)
	{
		this.schoolRid = schoolRid;
	}

	/**
	 * @return the programRid
	 */
	public Long getProgramRid()
	{
		return programRid;
	}

	/**
	 * @param programRid
	 *            the programRid to set
	 */
	public void setProgramRid(Long programRid)
	{
		this.programRid = programRid;
	}

	/**
	 * @return the year
	 */
	public Integer getYear()
	{
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year)
	{
		this.year = year;
	}

	/**
	 * @return the admissions
	 */
	public Integer getAdmissions()
	{
		return admissions;
	}

	/**
	 * @param admissions
	 *            the admissions to set
	 */
	public void setAdmissions(Integer pValue)
	{
		// validate arguments
		if ( pValue == null ) pValue = 0;
		
		this.admissions = pValue;
	}

	/**
	 * @return the graduations
	 */
	public Integer getGraduations()
	{
		return graduations;
	}

	/**
	 * @param graduations
	 *            the graduations to set
	 */
	public void setGraduations(Integer graduations)
	{
		this.graduations = graduations;
	}

	/**
	 * @return the rid
	 */
	public Long getRid()
	{
		return rid;
	}

	/**
	 * @return the attritionA
	 */
	public Integer getAttritionA()
	{
		return attritionA;
	}
	/**
	 * @param attritionA
	 *            the attritionA to set
	 */
	public void setAttritionA(Integer pValue)
	{
		// validate arguments
		if ( pValue == null ) pValue = 0;
		this.attritionA = pValue;
	}
	/**
	 * @return the attritonP
	 */
	public Integer getAttritionP()
	{
		return attritionP;
	}
	/**
	 * @param attritonP
	 *            the attritonP to set
	 */
	public void setAttritionP(Integer pValue)
	{
		// validate arguments
		if ( pValue == null ) pValue = 0;
		this.attritionP = pValue;
	}

	/**
	 * @return the degreeRid
	 */
	public Long getDegreeRid()
	{
		return degreeRid;
	}
	/**
	 * @param degreeRid the degreeRid to set
	 */
	public void setDegreeRid(Long pValue)
	{
		// validate arguments
		if ( pValue == null ) pValue = 1L;
		this.degreeRid = pValue;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the nclexPassPercent
	 */
	public Double getNclexPassPercent()
	{
		return nclexPassPercent;
	}
	/**
	 * @param nclexPassPercent the nclexPassPercent to set
	 */
	public void setNclexPassPercent(Double nclexPassPercent)
	{
		this.nclexPassPercent = nclexPassPercent;
	}
	// ============== METHODS ==================
	public SchoolAnnualDataVo getVo()
	{
		SchoolAnnualDataVo oResponse = new SchoolAnnualDataVo(this.rid.toString());
		
		oResponse.setSchoolRid(this.schoolRid.toString());
		oResponse.setProgramRid(this.programRid.toString());	 
		oResponse.setDegreeRid(this.degreeRid.toString());
		oResponse.setYear(this.year.toString());
		oResponse.setAdmissions(this.admissions.toString()); 
		oResponse.setGraduations(this.graduations.toString());
		oResponse.setAttritionA(this.attritionA.toString());
		oResponse.setAttritionP(this.attritionP.toString());
		oResponse.setNclexPassPercent(this.nclexPassPercent.toString());
				 
		
		return oResponse;
	}
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SchoolAnnualDataDto other = (SchoolAnnualDataDto) obj;
		if (rid == null) {
			if (other.rid != null) return false;
		}
		else
			if (!rid.equals(other.rid)) return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SchoolAnnualDataDto [rid=" + rid + ", schoolRid=" + schoolRid + ", programRid=" + programRid
				+ ", degreeRid=" + degreeRid + ", year=" + year + ", admissions=" + admissions + ", graduations="
				+ graduations + ", attritionA=" + attritionA + ", attritionP=" + attritionP + "]";
	}
	
	

}
