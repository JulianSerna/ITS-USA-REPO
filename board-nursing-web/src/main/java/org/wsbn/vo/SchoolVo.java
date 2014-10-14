package org.wsbn.vo;

import java.io.Serializable;

public class SchoolVo implements Serializable

{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	
	private Long schoolRid;
	private String schoolName;
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
	public String getName()
	{
		return schoolName;
	}
	/**
	 * @param schoolName the schoolName to set
	 */
	public void setName(String schoolName)
	{
		this.schoolName = schoolName;
	}
	
		
}
