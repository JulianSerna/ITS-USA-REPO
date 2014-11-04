package org.wsbn.vo;

import java.io.Serializable;


public class NclexAnnualDataVo implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	// STATE

	
	private String			rid;
	private String			schoolRid;
	private String schoolName;
	private String				programGroupRid;
	private String groupName;
	private String				year;
	private String nclexPassPercent;

	
	
	// CONSTRUCTOR(S)
	public NclexAnnualDataVo() 
	{
		 
	}
	public NclexAnnualDataVo(String pRid) 
	{
		this.rid = pRid;
		this.nclexPassPercent  = "0";
	}

	/**
	 * @return the schoolRid
	 */
	public String getSchoolRid()
	{
		return schoolRid;
	}

	/**
	 * @param schoolRid
	 *            the schoolRid to set
	 */
	public void setSchoolRid(String schoolRid)
	{
		this.schoolRid = schoolRid;
	}

	
	
	
	
	
	
	
	
	
	/**
	 * @return the programGroupName
	 */
	public String getGroupName()
	{
		return groupName;
	}
	/**
	 * @param programGroupName the programGroupName to set
	 */
	public void setGroupName(String pGroupName)
	{
		this.groupName = pGroupName;
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
	 * @return the programRid
	 */
	public String getProgramGroupRid()
	{
		return programGroupRid;
	}

	/**
	 * @param programRid
	 *            the programRid to set
	 */
	public void setProgramGroupRid(String programGroupRid)
	{
		this.programGroupRid = programGroupRid;
	}

	/**
	 * @return the year
	 */
	public String getYear()
	{
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year)
	{
		this.year = year;
	}

	


	/**
	 * @return the rid
	 */
	public String getRid()
	{
		return rid;
	}

	
	
	
	
	
	/**
	 * @return the nclexPassPercent
	 */
	public String getNclexPassPercent()
	{
		return nclexPassPercent;
	}
	/**
	 * @param nclexPassPercent the nclexPassPercent to set
	 */
	public void setNclexPassPercent(String nclexPassPercent)
	{
		this.nclexPassPercent = nclexPassPercent;
	}
	 
	
	

}
