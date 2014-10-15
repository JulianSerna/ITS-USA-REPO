package org.wsbn.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;





@Entity
@Table(name = "SCHOOL_ANNUAL_DATA")
public class SchoolAnnualDataDto implements Serializable
{

	
	private static final long	serialVersionUID	= 1L;
	// STATE
		
	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long rid;
	
	@Column(name = "SCHOOL_RID")
	private Long schoolRid;

	@Column(name = "PROGRAM_RID")
	private Long programRid;
	
	@Column(name = "YEAR")
	private Integer year;
	
	@Column(name = "ADMISSIONS")
	private Integer admissions;
	
	@Column(name = "GRADUATIONS")
	private Integer graduations;
	
	
	// CONSTRUCTOR(S)
	public SchoolAnnualDataDto() 
	{

	}


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
	 * @return the programRid
	 */
	public Long getProgramRid()
	{
		return programRid;
	}


	/**
	 * @param programRid the programRid to set
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
	 * @param year the year to set
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
	 * @param admissions the admissions to set
	 */
	public void setAdmissions(Integer admissions)
	{
		this.admissions = admissions;
	}


	/**
	 * @return the graduations
	 */
	public Integer getGraduations()
	{
		return graduations;
	}


	/**
	 * @param graduations the graduations to set
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


	/* (non-Javadoc)
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


	/* (non-Javadoc)
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
	
	
	
	// METHODS
	
	
	
	
	
	

	
	
	
	
}
