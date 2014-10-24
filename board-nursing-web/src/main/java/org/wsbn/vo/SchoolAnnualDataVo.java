package org.wsbn.vo;

import java.io.Serializable;

import org.wsbn.dto.SchoolAnnualDataDto;
 
public class SchoolAnnualDataVo implements Serializable
{

	
	private static final long	serialVersionUID	= 1L;
	// STATE
		
	 
	private String rid;
	private String schoolRid;
	private String programRid;
	private String degreeRid;
	private String year;
	private String admissions;
	private String graduations;
	private String attritionA;
	private String attritionP;
	
	// transient
	private String[] programRids;
	private String schoolName;
	private String programName;
	private String degreeName;
	
	
	// CONSTRUCTOR(S)
	public  SchoolAnnualDataVo()
	{
		
	}
	public SchoolAnnualDataVo(String pRid) 
	{
		
		this.rid = pRid;
		
	}

	
	
	// GETTER/SETTERS 
	public String getSchoolRid()
	{
		return schoolRid;
	}

 
	public void setSchoolRid(String schoolRid)
	{
		this.schoolRid = schoolRid;
	}

 
	public String getProgramRid()
	{
		return programRid;
	}

 
	public void setProgramRid(String programRid)
	{
		this.programRid = programRid;
	}
	 


	 
	public String getYear()
	{
		return year;
	}


	 
	public void setYear(String year)
	{
		this.year = year;
	}

 
	public String getAdmissions()
	{
		return admissions;
	}

 
	public void setAdmissions(String admissions)
	{
		this.admissions = admissions;
	}

 
	public String getGraduations()
	{
		return graduations;
	}

 
	public void setGraduations(String graduations)
	{
		this.graduations = graduations;
	}


	 
	public String getRid()
	{
		return rid;
	}

	 
	public String getSchoolName()
	{
		return schoolName;
	}

 
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	

	 
	public String getProgramName()
	{
		return programName;
	}
	public void setProgramName(String programName)
	{
		this.programName = programName;
	}
	/**
	 * @return the programRids
	 */
	public String[] getProgramRids()
	{
		return programRids;
	}
	/**
	 * @param programRids the programRids to set
	 */
	public void setProgramRids(String[] programRids)
	{
		this.programRids = programRids;
	}
	/**
	 * @return the attritionA
	 */
	public String getAttritionA()
	{
		return attritionA;
	}
	/**
	 * @param attritionA the attritionA to set
	 */
	public void setAttritionA(String attritionA)
	{
		this.attritionA = attritionA;
	}
	/**
	 * @return the attritionP
	 */
	public String getAttritionP()
	{
		return attritionP;
	}
	/**
	 * @param attritionP the attritionP to set
	 */
	public void setAttritionP(String attritionP)
	{
		this.attritionP = attritionP;
	}
	
	public String getDegreeRid()
	{
		return this.degreeRid;
	}
	/**
	 * @param attritionP the attritionP to set
	 */
	public void setDegreeRid(String pValue)
	{
		this.degreeRid = pValue;
	}
	/**
	 * @return the degreeName
	 */
	public String getDegreeName()
	{
		return degreeName;
	}
	/**
	 * @param degreeName the degreeName to set
	 */
	public void setDegreeName(String degreeName)
	{
		this.degreeName = degreeName;
	}

	
 
 
	 
 

	
	
	
	
}
